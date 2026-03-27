package workers;

import dtos.DigestNotificationJob;
import dtos.NotificationJob;
import dtos.NotificationSuccess;
import messages.DigestMessage;
import messages.Message;
import messages.StringMessage;
import queues.BiNotificationQueue;

import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class DigestPollingWorker<U,D extends DigestMessage,T extends DigestNotificationJob<D>>  implements Worker{
    protected final BiNotificationQueue<? extends NotificationJob<StringMessage>,? extends DigestNotificationJob<D>> queue;
    protected final Lock lock;
    protected final Condition notEmpty;
    protected final Condition notFull;
    protected DigestPollingWorker(BiNotificationQueue<? extends NotificationJob<StringMessage>,? extends DigestNotificationJob<D>> queue) {

        this.queue = queue;
        Lock lock = new ReentrantLock();
        this.lock = lock;
        this.notEmpty = lock.newCondition();
        this.notFull = lock.newCondition();
    }
    @Override
    public  NotificationSuccess call() {
        /*The worker thread will continuously peek the queue*/
        Object job;
        lock.lock();
        try{
            while(queue.isEmpty()){
                try {
                    notEmpty.await();
                } catch (InterruptedException e) {
                    System.out.println("Worker interrupted");
                }

            }
            job =  queue.pollDigested();

            notFull.signal();

        }
        finally {
            lock.unlock();
        }
        return task(job);
    }

    protected  abstract  NotificationSuccess task(Object job);
}
