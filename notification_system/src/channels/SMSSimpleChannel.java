package channels;

import dtos.SMSNotificationJob;
import enums.ChannelEnum;
import queues.SimpleNotificationQueue;
import workers.SMSPollingWorker;
import workers.Worker;

public class SMSSimpleChannel implements SimpleChannel<SMSNotificationJob> {
    private final SimpleNotificationQueue<SMSNotificationJob> queue;

    public SMSSimpleChannel(SimpleNotificationQueue<SMSNotificationJob> queue) {
        this.queue = queue;
    }

    @Override
    public SimpleNotificationQueue<SMSNotificationJob> getQueue() {
        return queue;
    }
    @Override
    public Worker getWorker(SimpleNotificationQueue<SMSNotificationJob> queue) {
        return new SMSPollingWorker(queue);
    }
    @Override
    public int maxRetryCount() {
        return 3;
    }
    @Override
    public ChannelEnum supports() {
        return ChannelEnum.SMS;
    }
}
