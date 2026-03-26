package queues;

import channels.Channel;
import dtos.SMSNotificationJob;
import enums.ChannelEnum;
import factories.ChannelFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class SMSFinalDeliveryQueue extends FinalDeliveryQueue<SMSNotificationJob> {

    protected SMSFinalDeliveryQueue(ChannelFactory channelFactory) {
        super(channelFactory);
        Channel<SMSNotificationJob> channel = channelFactory.getChannel(ChannelEnum.SMS);

        ExecutorService executor = Executors.newFixedThreadPool(MAX_WORKERS_COUNT);
        for(int i = 0;i<FinalDeliveryQueue.MAX_WORKERS_COUNT;i++)
        {
            executor.submit(channel.getWorker(this,super.lock,super.lock.newCondition(),super.lock.newCondition()));
        }
    }
}
