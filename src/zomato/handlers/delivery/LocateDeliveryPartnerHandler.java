package zomato.handlers.delivery;

import zomato.models.Delivery;
import zomato.models.DeliveryPerson;
import zomato.strategies.LocateDriverStrategy;

public class LocateDeliveryPartnerHandler extends DeliveryHandler{
    private LocateDriverStrategy strategy;
    public LocateDeliveryPartnerHandler(LocateDriverStrategy strategy)
    {
        super();
        this.strategy = strategy;
    }
    @Override
    public void handle(Delivery delivery) {

       DeliveryPerson person = strategy.locate(delivery);
       delivery.setDeliveryPerson(person);
    }
}
