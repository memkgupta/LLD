package channels;

import dtos.WhatsappNotificationJob;
import enums.ChannelEnum;
import messages.WhatsappDigestMessage;
import queues.DigestQueue;
import queues.SimpleNotificationQueue;
import queues.WhatsappDigestQueue;
import workers.WhatsappDigestPollingWorker;
import workers.Worker;

public class WhatsappDigestChannel implements DigestChannel<WhatsappNotificationJob, WhatsappDigestMessage> {
   private final SimpleNotificationQueue<WhatsappNotificationJob> queue;

    public WhatsappDigestChannel(SimpleNotificationQueue<WhatsappNotificationJob> queue) {
        this.queue = queue;
    }

    @Override
    public SimpleNotificationQueue<WhatsappNotificationJob> getQueue() {
        return queue;
    }

    @Override
    public Worker getWorker(DigestQueue<WhatsappDigestMessage> queue) {
        return new WhatsappDigestPollingWorker((WhatsappDigestQueue) queue);
    }


//    @Override
//    public <Q> Worker getWorker(WhatsappDigestQueue queue) {
//        return new WhatsappDigestPollingWorker(queue);
//    }



    @Override
    public int maxRetryCount() {
        return 3;
    }

    @Override
    public ChannelEnum supports() {
        return ChannelEnum.WHATSAPP;
    }
}
