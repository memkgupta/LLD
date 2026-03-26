package messages;

public abstract class Message<T> {
protected final T content;
protected final String userId;

    public Message(T content, String userId) {
        this.content = content;
        this.userId = userId;
    }

    public T getContent() {
        return content;
    }

    public String getUserId() {
        return userId;
    }
}
