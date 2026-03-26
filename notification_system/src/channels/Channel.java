package channels;

import dtos.NotificationJob;
import enums.ChannelEnum;
import queues.NotificationQueue;
import workers.Worker;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;


// T for the NotificationJob type
public interface Channel<T extends NotificationJob> {
    NotificationQueue<T> getInitialQueue();
    NotificationQueue<T> getFinalQueue();
    Worker<T> getWorker(NotificationQueue<T> queue, Lock lock, Condition notEmpty, Condition notFull);
    int maxRetryCount();
    ChannelEnum supports();
}
