package workers;

import dtos.NotificationSuccess;

import java.util.concurrent.Callable;

public interface Worker<T> extends Callable<NotificationSuccess> {
}
