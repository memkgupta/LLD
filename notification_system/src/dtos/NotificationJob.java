package dtos;

import enums.ChannelEnum;
import messages.Message;

public class NotificationJob<T> {
    /*priority decreases from 0->3*/
    private final int priority;
    private int retryCount;
    private final ChannelEnum channel;
    private final Message<T> message;
    private final String userId;
    public NotificationJob(Message<T> message, ChannelEnum channel, int priority, String userId) {
        this.message = message;
        this.channel = channel;
        this.priority = priority;
        this.userId = userId;
    }
    public NotificationJob(NotificationJob<T> job) {
        this.message = job.message;
        this.channel = job.channel;
        this.priority = job.priority;
        this.retryCount = job.retryCount;
        this.userId = job.userId;
    }
    public int getPriority() {
        return priority;
    }

    public ChannelEnum getChannel() {
        return channel;
    }

    public Message getMessage() {
        return message;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    public String getUserId() {
        return userId;
    }
}
