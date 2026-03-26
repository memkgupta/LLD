package dtos;

import enums.ChannelEnum;
import messages.Message;

public class SMSNotificationJob extends NotificationJob {
    public SMSNotificationJob(Message message,  int priority) {
        super(message, ChannelEnum.SMS, priority);
    }
}
