package zomato.producer_consumers.delivery;

import zomato.ProducerConsumerManager;
import zomato.interfaces.Consumer;
import zomato.interfaces.Producer;
import zomato.managers.DeliveryManager;
import zomato.models.Delivery;

public class DeliveryQueueConsumer implements Consumer<Delivery> {
    private final DeliveryManager deliveryManager;

    public DeliveryQueueConsumer(DeliveryManager deliveryManager) {
        this.deliveryManager = deliveryManager;
    }

    @Override
    public void subscribe(Producer<Delivery> producer) {
        ProducerConsumerManager.getInstance().registerConsumer(
                producer,this
        );
    }


    @Override
    public void notification() {
        deliveryManager.handleDelivery(DeliveryQueue.getInstance().consume());
    }
}
