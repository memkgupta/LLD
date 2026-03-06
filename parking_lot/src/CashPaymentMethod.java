public class CashPaymentMethod extends PaymentMethod {
    @Override
    void pay(double amount) {
        System.out.printf("Paid %.2f via cash", amount);
    }

    @Override
    PaymentMethodEnum supports() {
        return PaymentMethodEnum.CASH;
    }
}
