package zomato.producer_consumers.kitchen;

import zomato.models.Order;
import zomato.producer_consumers.AbstractProducerConsumerQueue;

import java.util.LinkedList;

public class KitchenQueue extends AbstractProducerConsumerQueue<Order> {

    // ---- singleton instance ----
    private static KitchenQueue INSTANCE;

    // ---- private constructor ----
    private KitchenQueue() {
        super(new LinkedList<>());
    }

    // ---- global access point ----
    public static KitchenQueue getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new KitchenQueue();
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
            throw new IllegalStateException("kitchen queue is empty");
        }
        return queue.remove();
    }
}
