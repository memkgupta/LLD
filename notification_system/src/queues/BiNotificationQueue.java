package queues;

public interface BiNotificationQueue<T,D> {
    D pollDigested();
    D peekDigested();
    boolean offer(T job);
    boolean isEmpty();
    boolean isFull();
}
