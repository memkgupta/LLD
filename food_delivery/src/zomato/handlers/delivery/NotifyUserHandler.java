package zomato.handlers.delivery;

import zomato.models.Delivery;
import zomato.strategies.NotifyUserStrategy;

public class NotifyUserHandler extends DeliveryHandler{
   private NotifyUserStrategy strategy;
   public NotifyUserHandler(NotifyUserStrategy strategy) {
        this.strategy = strategy;
   }
    @Override
    public void handle(Delivery delivery) {
        // notify the user for their food order

        strategy.notify(delivery);
    }
}
