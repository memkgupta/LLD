package zomato.producer_consumers;

import java.util.Queue;

public abstract class AbstractProducerConsumerQueue<T> {

    protected Queue<T> queue;

    protected AbstractProducerConsumerQueue(Queue<T> queue) {
        this.queue = queue;
    }

    // produce an item
    public abstract void produce(T item) ;

    // consume an item
    public abstract T consume() ;

    // optional helper
    protected int size() {
        return queue.size();
    }
}