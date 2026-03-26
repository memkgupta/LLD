package workers;

import dtos.NotificationJob;
import dtos.NotificationSuccess;
import messages.Message;
import queues.NotificationQueue;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public abstract class PollingWorker<C,D extends Message<C>,T extends NotificationJob<D>> implements Worker<T> {
    protected final NotificationQueue<T> queue;
    protected final Lock lock;
    protected final Condition notEmpty;
    protected final Condition notFull;
    protected PollingWorker(NotificationQueue<T> queue, Lock lock, Condition notEmpty, Condition notFull) {
        this.queue = queue;
        this.lock = lock;
        this.notEmpty = notEmpty;
        this.notFull = notFull;
    }
    @Override
    public NotificationSuccess call() {
       /*The worker thread will continuously peek the queue*/
       T job;
        lock.lock();
        try{
            while(queue.isEmpty()){
                try {
                    notEmpty.await();
                } catch (InterruptedException e) {
                    System.out.println("Worker interrupted");
                }

            }
            job = queue.poll();

            notFull.signal();

        }
        finally {
            lock.unlock();
        }
        return task(job);
    }
    protected abstract NotificationSuccess task(T job);
}
