package zomato.managers;

import zomato.ProducerConsumerManager;
import zomato.enums.OrderStatus;
import zomato.handlers.*;
import zomato.interfaces.Consumer;
import zomato.interfaces.OrderConsumer;
import zomato.interfaces.OrderProducer;
import zomato.interfaces.Producer;
import zomato.models.Delivery;
import zomato.models.Order;
import zomato.services.DeliveryService;

public class OrderManager {
private final DeliveryService deliveryService;
    public OrderManager (DeliveryService deliveryService, Producer<Order> orderProducer) {
        this.deliveryService = deliveryService;
    }

    public void handleOrder(Order order)
    {

        OrderHandler handler = new CheckAvailabilityHandler()
                .setNext(new EstimateDeliveryTimeHandler())
                .setNext(new CreateDeliveryHandler())
                .setNext(new NotifyKitchenHandler());
        handler.handle(order);
    }

    public void orderPrepared(Order order)
    {
        // put the order for delivery or pickup

    }
    public void updatePayment(Order order)
    {

    }
    public void updateFinalState(Order order, OrderStatus orderStatus)
    {

    }

}
