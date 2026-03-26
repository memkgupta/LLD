package queues;

public interface BiNotificationQueue<T,D> extends NotificationQueue<T>{
    D pollDigested();
    D peekDigested();
}
