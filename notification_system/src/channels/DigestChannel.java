package channels;

import dtos.DigestNotificationJob;
import enums.ChannelEnum;
import messages.DigestMessage;
import queues.DigestQueue;
import queues.SimpleNotificationQueue;
import workers.Worker;

public interface DigestChannel<T extends DigestNotificationJob,D extends DigestMessage> extends Channel {
    SimpleNotificationQueue<T> getQueue();
    //    NotificationQueue<T> getFinalQueue();
   Worker getWorker(DigestQueue<D> queue);

    int maxRetryCount();

}
