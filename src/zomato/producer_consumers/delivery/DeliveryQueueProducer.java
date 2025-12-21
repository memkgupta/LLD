package zomato.producer_consumers.delivery;

import zomato.ProducerConsumerManager;
import zomato.interfaces.Consumer;
import zomato.interfaces.Producer;
import zomato.models.Delivery;

public class DeliveryQueueProducer implements Producer<Delivery> {
    @Override
    public void produce(Delivery item) {
        DeliveryQueue.getInstance().produce(item);
        ProducerConsumerManager.getInstance().notifyConsumers(this);
    }


}
