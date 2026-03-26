package workers;

import dtos.NotificationJob;
import dtos.NotificationSuccess;
import dtos.SMSNotificationJob;
import messages.StringMessage;
import queues.NotificationQueue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class SMSPollingWorker extends PollingWorker<String , StringMessage,SMSNotificationJob> {
    protected SMSPollingWorker(NotificationQueue<SMSNotificationJob> queue, Lock lock, Condition notEmpty, Condition notFull) {
        super(queue, lock, notEmpty, notFull);
    }

    @Override
    protected NotificationSuccess task(SMSNotificationJob job) {
        return null;
    }
}
