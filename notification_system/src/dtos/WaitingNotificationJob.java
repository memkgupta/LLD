package dtos;

import enums.ChannelEnum;
import messages.Message;

public class WaitingNotificationJob extends NotificationJob{
    public WaitingNotificationJob(NotificationJob job) {
        super(job);
    }
}
