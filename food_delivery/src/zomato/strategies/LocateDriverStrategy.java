package zomato.strategies;

import zomato.models.Delivery;
import zomato.models.DeliveryPerson;
import zomato.services.DeliveryService;

public abstract class LocateDriverStrategy {
    protected DeliveryService deliveryService;
    public LocateDriverStrategy(DeliveryService deliveryService)
    {
        this.deliveryService = deliveryService;
    }
   public abstract DeliveryPerson locate(Delivery delivery);
}
