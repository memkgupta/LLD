package queues;

public interface SimpleNotificationQueue<T> {
T peek();
T poll();
boolean offer(T job);
boolean isEmpty();
boolean isFull();
}
