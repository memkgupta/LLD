package dtos;

import enums.ChannelEnum;
import messages.DigestMessage;
import messages.Message;

import java.util.ArrayList;

public class DigestNotificationJob<T  extends DigestMessage> extends NotificationJob<T> {
    private final String userId;
    public DigestNotificationJob( ChannelEnum channel, int priority, String userId , T message)  {
        super(message, channel, priority,userId);
        this.userId = userId;
    }
    public String getUserId() {
        return userId;
    }

}
