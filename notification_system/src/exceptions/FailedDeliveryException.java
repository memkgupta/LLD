package exceptions;

import dtos.NotificationJob;

public class FailedDeliveryException extends RuntimeException
{
    private final NotificationJob job;
    public FailedDeliveryException(NotificationJob job)
    {
        super("Max retry count reached");
        this.job = job;
    }
    public NotificationJob getJob()
    {
        return job;
    }
}
