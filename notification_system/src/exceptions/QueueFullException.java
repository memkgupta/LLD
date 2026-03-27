package exceptions;

import dtos.NotificationJob;

public class QueueFullException extends RuntimeException {
    private final NotificationJob job;
    public QueueFullException( NotificationJob job) {
        super("Queue full");
        this.job = job;
    }
}
