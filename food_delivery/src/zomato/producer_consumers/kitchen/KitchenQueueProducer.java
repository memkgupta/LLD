package zomato.producer_consumers.kitchen;

import zomato.ProducerConsumerManager;
import zomato.interfaces.Producer;
import zomato.models.Order;

public class KitchenQueueProducer implements Producer<Order> {
    @Override
    public void produce(Order item) {
        KitchenQueue.getInstance().produce(item);
        ProducerConsumerManager.getInstance().notifyConsumers(this);
    }
}
