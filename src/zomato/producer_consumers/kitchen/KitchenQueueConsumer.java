package zomato.producer_consumers.kitchen;

import zomato.ProducerConsumerManager;
import zomato.interfaces.Consumer;
import zomato.interfaces.Producer;
import zomato.managers.KitchenManager;
import zomato.models.Order;

public class KitchenQueueConsumer implements Consumer<Order> {
    private final KitchenManager kitchenManager;

    public KitchenQueueConsumer(KitchenManager kitchenManager) {
        this.kitchenManager = kitchenManager;
    }


    @Override
    public void subscribe(Producer<Order> producer) {
        ProducerConsumerManager.getInstance().registerConsumer(
                producer,this
        );
    }

    @Override
    public void notification() {
    this.kitchenManager.handleKitchenOrder(KitchenQueue.getInstance().consume());
    }
}
