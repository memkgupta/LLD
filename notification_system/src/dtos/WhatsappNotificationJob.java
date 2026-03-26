package dtos;

import enums.ChannelEnum;
import messages.WhatsappDigestMessage;

public class WhatsappNotificationJob extends DigestNotificationJob<WhatsappDigestMessage> {
    public WhatsappNotificationJob( int priority, String userId, WhatsappDigestMessage message) {
        super(ChannelEnum.WHATSAPP, priority, userId, message);
    }
}
