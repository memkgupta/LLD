package zomato.interfaces;

import zomato.models.Order;

@FunctionalInterface
public interface ConsumeFunction {
void consume(Order order);
}
