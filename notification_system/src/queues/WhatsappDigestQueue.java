package queues;

import dtos.DigestNotificationJob;
import dtos.NotificationJob;
import enums.ChannelEnum;
import messages.StringMessage;
import messages.WhatsappDigestMessage;

import java.util.List;

public class WhatsappDigestQueue extends DigestQueue<WhatsappDigestMessage> {
    public WhatsappDigestQueue(int capacity) {
        super(capacity);
    }

    @Override
    protected DigestNotificationJob<WhatsappDigestMessage> mergeJobs(List<NotificationJob<StringMessage>> jobs) {
        String userId = jobs.getFirst().getUserId();
        int priority = jobs.getFirst().getPriority();
        WhatsappDigestMessage message = new WhatsappDigestMessage(userId);
        for (NotificationJob<StringMessage> job : jobs) {
            message.addMessage(job.getMessage());
        }
        return new DigestNotificationJob<>(ChannelEnum.WHATSAPP, priority, userId, message);
    }

}





