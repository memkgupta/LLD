package zomato.interfaces;

import zomato.models.Order;

public abstract class OrderProducer implements Producer<Order>{
   public abstract void produce(Order order);
   public abstract void notifyConsumers();

}
