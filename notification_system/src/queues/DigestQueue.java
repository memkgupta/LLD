package queues;

import dtos.DigestNotificationJob;
import dtos.NotificationJob;
import messages.DigestMessage;
import messages.Message;
import messages.StringMessage;

import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * D → Final DIGESTED message type (e.g. WhatsappDigestMessage)
 *     Must extend DigestMessage because it represents a batch/aggregated message
 *
 * T → RAW message type (e.g. StringMessage, EmailMessage)
 *     Must extend Message because it's a single notification payload
 *
 * This queue:
 * - Accepts raw NotificationJob<T>
 * - Groups them (by userId)
 * - Produces DigestNotificationJob<D> after batching
 */
public abstract class DigestQueue<
        D extends DigestMessage  // Final aggregated message type
//        T extends St         // Raw message type
        > implements BiNotificationQueue<
        NotificationJob<StringMessage>,        // Input → raw jobs
        DigestNotificationJob<D>   // Output → digested jobs
        > {

    /**
     * Buffer for batching:
     * Key   → userId
     * Value → list of raw jobs for that user
     *
     * Example:
     * user1 → [msg1, msg2, msg3]
     */
    private final Map<String, List<NotificationJob<StringMessage>>> buffer = new HashMap<>();

    /**
     * Queue of READY digested jobs
     * These are consumed by workers
     */
    private final Queue<DigestNotificationJob<D>> readyQueue = new LinkedList<>();

    /**
     * Max number of digested jobs allowed in readyQueue
     */
    private final int capacity;

    /**
     * Lock + conditions for producer-consumer pattern
     */
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition notEmpty = lock.newCondition(); // for consumers
    private final Condition notFull = lock.newCondition();  // for producers

    public DigestQueue(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Adds a raw job into buffer and checks if it can be digested
     *
     * Flow:
     * 1. Group by userId
     * 2. If enough messages (>= 3), merge them
     * 3. Move to readyQueue
     */
    private void addToDigest(NotificationJob<StringMessage> job) {
        buffer.computeIfAbsent(job.getUserId(), k -> new ArrayList<>()).add(job);

        List<NotificationJob<StringMessage>> jobs = buffer.get(job.getUserId());

        // batching condition (can be time-based or size-based)
        if (jobs.size() >= 3) {
            DigestNotificationJob<D> digest = mergeJobs(jobs);

            readyQueue.offer(digest);     // push to output queue
            buffer.remove(job.getUserId()); // clear buffer for that user
        }
    }

    /**
     * Converts a list of raw jobs → single digest job
     *
     * Example:
     * [msg1, msg2, msg3] → DigestMessage → DigestNotificationJob
     */
    protected abstract DigestNotificationJob<D> mergeJobs(List<NotificationJob<StringMessage>> jobs);

    /**
     * Producer API → add raw job
     */
    @Override
    public boolean offer(NotificationJob<StringMessage> job) {
        lock.lock();
        try {
            while (isFull()) {
                notFull.await();
            }

            addToDigest(job);

            if (!readyQueue.isEmpty()) {
                notEmpty.signal(); // notify consumers
            }

            return true;

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Consumer API → get digested job (blocking)
     */
    @Override
    public DigestNotificationJob<D> pollDigested() {
        lock.lock();
        try {
            while (readyQueue.isEmpty()) {
                notEmpty.await();
            }

            DigestNotificationJob<D> job = readyQueue.poll(); // FIXED generic
            notFull.signal(); // notify producers

            return job;

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Non-blocking peek
     */
    @Override
    public DigestNotificationJob<D> peekDigested() {
        lock.lock();
        try {
            return readyQueue.peek();
        } finally {
            lock.unlock();
        }
    }

    /**
     * Whether there are digested jobs available
     */
    @Override
    public boolean isEmpty() {
        return readyQueue.isEmpty();
    }

    /**
     * Whether readyQueue is full
     * (NOTE: buffer is unbounded currently ⚠️)
     */
    @Override
    public boolean isFull() {
        return readyQueue.size() >= capacity;
    }
}