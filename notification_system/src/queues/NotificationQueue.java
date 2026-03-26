package queues;

import dtos.NotificationJob;

public interface NotificationQueue<T> {
T peek();
T poll();
boolean offer(T job);
boolean isEmpty();
boolean isFull();
}
