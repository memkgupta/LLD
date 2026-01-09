package zomato.strategies;

import zomato.models.Delivery;

public abstract class NotifyUserStrategy {
    abstract public void notify(Delivery delivery);
}
