public abstract class PaymentMethod {
   abstract void pay(double amount);
   abstract PaymentMethodEnum supports();
}
