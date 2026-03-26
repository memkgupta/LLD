package workers;

import dtos.NotificationJob;
import dtos.NotificationSuccess;
import dtos.SMSNotificationJob;
import queues.NotificationQueue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class SMSPollingWorker extends PollingWorker<SMSNotificationJob> {
    protected SMSPollingWorker(NotificationQueue<SMSNotificationJob> queue, Lock lock, Condition notEmpty, Condition notFull) {
        super(queue, lock, notEmpty, notFull);
    }

    @Override
    protected NotificationSuccess task(NotificationJob job) {
        return null;
    }
}
