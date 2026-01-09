package zomato.producer_consumers.order;

import zomato.ProducerConsumerManager;
import zomato.interfaces.Consumer;
import zomato.interfaces.Producer;
import zomato.managers.OrderManager;
import zomato.models.Order;

public class OrderQueueConsumer implements Consumer<Order> {
    private final OrderManager orderManager;

    public OrderQueueConsumer(OrderManager orderManager) {
        this.orderManager = orderManager;
    }

    @Override
    public void subscribe(Producer<Order> producer) {
        ProducerConsumerManager.getInstance().registerConsumer(producer,this);
    }

    @Override
    public void notification() {
    orderManager.handleOrder(OrderQueue.getInstance().consume());
    }
}
