package dtos;

import enums.ChannelEnum;
import messages.DigestMessage;
import messages.Message;

import java.util.ArrayList;

public class DigestNotificationJob extends NotificationJob<DigestMessage> {
    private final String userId;
    public DigestNotificationJob( ChannelEnum channel, int priority, String userId , DigestMessage message)  {
        super(message, channel, priority);
        this.userId = userId;
    }
    public String getUserId() {
        return userId;
    }

}
