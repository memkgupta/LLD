package zomato.interfaces;

import zomato.models.Order;

public abstract class OrderConsumer implements Consumer<Order> {
   public abstract void subscribe(OrderProducer producer);
   public abstract void startListening();
}
