package workers;

import dtos.NotificationSuccess;
import dtos.SMSNotificationJob;
import messages.StringMessage;
import queues.SimpleNotificationQueue;

public class SMSPollingWorker extends PollingWorker<String , StringMessage,SMSNotificationJob> {
    public SMSPollingWorker(SimpleNotificationQueue<SMSNotificationJob> queue) {

        super(queue);
//        Lock lock = new ReentrantLock();
//        Condition notEmpty = lock.newCondition();
//        Condition notFull = lock.newCondition();
    }

    @Override
    protected NotificationSuccess task(SMSNotificationJob job) {
        return null;
    }
}
