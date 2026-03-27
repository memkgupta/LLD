package channels;

import dtos.NotificationJob;
import enums.ChannelEnum;
import queues.SimpleNotificationQueue;
import workers.Worker;


// T for the NotificationJob type
public interface SimpleChannel<T extends NotificationJob> extends Channel {
    SimpleNotificationQueue<T> getQueue();
//    NotificationQueue<T> getFinalQueue();
    Worker getWorker(SimpleNotificationQueue<T> queue);

    int maxRetryCount();

}
