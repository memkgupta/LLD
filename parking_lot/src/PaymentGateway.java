public class PaymentGateway {
    private final PaymentMethodFactory paymentMethodFactory;
    public PaymentGateway(PaymentMethodFactory paymentMethodFactory) {
        this.paymentMethodFactory = paymentMethodFactory;
    }
    public void pay(double amount , PaymentMethodEnum method){
       paymentMethodFactory.getMethod(method).pay(amount);
    }
}
