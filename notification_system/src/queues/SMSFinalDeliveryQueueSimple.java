package queues;

import channels.SimpleChannel;
import dtos.SMSNotificationJob;
import enums.ChannelEnum;
import factories.ChannelFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SMSFinalDeliveryQueueSimple extends FinalDeliveryQueueSimple<SMSNotificationJob> {

    protected SMSFinalDeliveryQueueSimple(ChannelFactory channelFactory) {
        super(channelFactory);
        SimpleChannel<SMSNotificationJob> simpleChannel = (SimpleChannel<SMSNotificationJob>) channelFactory.getChannel(ChannelEnum.SMS);

        ExecutorService executor = Executors.newFixedThreadPool(MAX_WORKERS_COUNT);
        for(int i = 0; i< FinalDeliveryQueueSimple.MAX_WORKERS_COUNT; i++)
        {
            executor.submit(simpleChannel.getWorker(this));
        }
    }
}
