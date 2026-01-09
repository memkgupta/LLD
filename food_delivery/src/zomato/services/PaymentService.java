package zomato.services;

import zomato.strategies.PaymentStrategy;

public class PaymentService {
    public void pay(PaymentStrategy paymentStrategy)
    {
        paymentStrategy.pay();
    }
}
