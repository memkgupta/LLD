package queues;

import dtos.DigestNotificationJob;
import dtos.NotificationJob;
import messages.DigestMessage;
import messages.StringMessage;

import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public abstract class DigestQueue<D extends DigestMessage> implements BiNotificationQueue<NotificationJob<StringMessage>,DigestNotificationJob> {
    private final Map<String, List<NotificationJob<StringMessage >>> buffer = new HashMap<>();
    private final Queue<DigestNotificationJob<D>> readyQueue = new LinkedList<>();
    private final int capacity;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition notEmpty = lock.newCondition();
    private final Condition notFull = lock.newCondition();
    public DigestQueue(int capacity) {
        this.capacity = capacity;
    }
    private void addToDigest(NotificationJob<StringMessage> job) {
        buffer.computeIfAbsent(job.getUserId(), k -> new ArrayList<>()).add(job);
        List<NotificationJob<StringMessage>> jobs = buffer.get(job.getUserId());
        if (jobs.size() >= 3) {
            DigestNotificationJob<D> digest = mergeJobs(jobs);
            readyQueue.offer(digest);
            buffer.remove(job.getUserId());
        }
    }
    protected abstract DigestNotificationJob<D> mergeJobs(List<NotificationJob<StringMessage>> jobs);
    @Override
    public boolean offer(NotificationJob<StringMessage> job) {
        lock.lock();
        try {
            while (isFull()) {
                notFull.await();
            }

            addToDigest(job);

            if (!readyQueue.isEmpty()) {
                notEmpty.signal();
            }

            return true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        } finally {
            lock.unlock();
        }
    }
    @Override
    public NotificationJob<StringMessage> poll() {
       throw new IllegalStateException("Not implemented");
    }
    @Override
    public NotificationJob<StringMessage> peek() {
        throw new IllegalStateException("Not implemented");

    }
    @Override
    public DigestNotificationJob<D> pollDigested() {
        lock.lock();
        try {
            while (readyQueue.isEmpty()) {
                notEmpty.await();
            }

            DigestNotificationJob job = readyQueue.poll();
            notFull.signal();
            return job;

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        } finally {
            lock.unlock();
        }
    }
    @Override
    public DigestNotificationJob<D> peekDigested() {
        lock.lock();
        try {
            return readyQueue.peek();
        } finally {
            lock.unlock();
        }
    }
    @Override
    public boolean isEmpty() {
        return readyQueue.isEmpty();
    }

    @Override
    public boolean isFull() {
        return readyQueue.size() >= capacity;
    }
}