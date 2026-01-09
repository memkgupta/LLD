package zomato.producer_consumers.order;

import zomato.models.Order;
import zomato.producer_consumers.AbstractProducerConsumerQueue;

import java.util.LinkedList;

public class OrderQueue extends AbstractProducerConsumerQueue<Order> {

    // ---- singleton instance ----
    private static OrderQueue INSTANCE;

    // ---- private constructor ----
    private OrderQueue() {
        super(new LinkedList<>());
    }

    // ---- global access point ----
    public static OrderQueue getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new OrderQueue();
        }
        return INSTANCE;
    }

    @Override
    public void produce(Order item) {
        if (item == null) {
            throw new IllegalArgumentException("order cannot be null");
        }
        queue.add(item);
    }

    @Override
    public Order consume() {
        if (queue.isEmpty()) {
            throw new IllegalStateException("order queue is empty");
        }
        return queue.remove();
    }
}
