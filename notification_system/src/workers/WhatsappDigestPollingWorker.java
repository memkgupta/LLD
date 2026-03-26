package workers;

import dtos.DigestNotificationJob;
import dtos.NotificationJob;
import dtos.NotificationSuccess;
import dtos.WhatsappNotificationJob;
import messages.StringMessage;
import messages.WhatsappDigestMessage;
import queues.NotificationQueue;

import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class WhatsappDigestPollingWorker extends PollingWorker<List<StringMessage>,WhatsappDigestMessage, WhatsappNotificationJob> {
    protected WhatsappDigestPollingWorker(NotificationQueue<WhatsappNotificationJob> queue, Lock lock, Condition notEmpty, Condition notFull) {
        super(queue, lock, notEmpty, notFull);
    }


    @Override
    protected NotificationSuccess task(WhatsappNotificationJob job) {
        return null;
    }
}
