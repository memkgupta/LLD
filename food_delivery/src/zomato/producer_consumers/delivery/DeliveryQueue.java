package zomato.producer_consumers.delivery;

import zomato.models.Delivery;
import zomato.producer_consumers.AbstractProducerConsumerQueue;

import java.util.LinkedList;
import java.util.Queue;

public class DeliveryQueue extends AbstractProducerConsumerQueue<Delivery> {

    private static DeliveryQueue INSTANCE;
    public static DeliveryQueue getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DeliveryQueue();
        }
        return INSTANCE;
    }
    private DeliveryQueue() {
        super(new LinkedList<>());
    }
    @Override
    public void produce(Delivery item)  {
        this.queue.add(item);
    }

    @Override
    public Delivery consume()  {
        return this.queue.remove();
    }
}
