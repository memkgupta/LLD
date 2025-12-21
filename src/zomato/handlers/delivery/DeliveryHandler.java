package zomato.handlers.delivery;

import zomato.models.Delivery;

public abstract class DeliveryHandler {
    protected DeliveryHandler next;
    public DeliveryHandler setNext(DeliveryHandler next) {
        this.next = next;
        return next;
    }
    public abstract void handle(Delivery delivery);
}
