package zomato.managers;

import zomato.enums.OrderType;
import zomato.enums.PaymentMethod;
import zomato.handlers.delivery.*;
import zomato.models.Delivery;
import zomato.models.DeliveryPerson;
import zomato.models.Order;
import zomato.services.OrderService;
import zomato.strategies.*;

public class DeliveryManager {
    private final OrderService orderService;
    private final LocateDriverStrategy locateDriverStrategy;
public DeliveryManager(OrderService orderService, LocateDriverStrategy locateDriverStrategy)
{

    this.orderService = orderService;
    this.locateDriverStrategy = locateDriverStrategy;
}
public void handleDelivery(Delivery delivery)
{
    Order order = orderService.getOrder(delivery.getOrderId());
    DeliveryHandler handler = null;
    if(order.getOrderType().equals(OrderType.DELIVERY))
    {
        handler = new LocateDeliveryPartnerHandler(locateDriverStrategy)
                .setNext(
                        new NotifyDeliveryPersonHandler()
                )
                .setNext(
                        new NotifyUserHandler(new NotifyUserForDeliveryStrategy())
                )
        ;
    }
    else {
    handler = new NotifyUserHandler(
            new NotifyForOrderPickupStrategy()
    );
    }
    handler.handle(delivery);
}
public void assignDeliveryPerson(Delivery delivery , DeliveryPerson person)
{

}
public void pickOrder(Delivery delivery)
{

}
public void orderDelivered(Delivery delivery)
{
    DeliveryHandler handler = new NotifyUserHandler(new NotifyOrderReached());
    Order order = orderService.getOrder(delivery.getOrderId());
    if(order.getPaymentMethod().equals(PaymentMethod.CASH))
    {
        handler = handler.setNext(new AcceptPaymentHandler());
    }

    handler.setNext(
            new OTPVerificationHandler()
    ).setNext(
            new HandoverHandler()
    )
            .setNext(
                    new NotifyUserHandler(new NotifyOrderDeliveredStrategy())
            );
    handler.handle(delivery);

}
}
