package dtos;

import enums.ChannelEnum;
import messages.Message;
import messages.StringMessage;

public class SMSNotificationJob extends NotificationJob<StringMessage> {
    public SMSNotificationJob(StringMessage message,  int priority , String userId) {
        super(message, ChannelEnum.SMS, priority,userId);
    }
}
