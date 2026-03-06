public class UPIPaymentMethod extends PaymentMethod {
    @Override
    void pay(double amount) {
        System.out.printf("Paid %.2f via UPI\n", amount);
    }

    @Override
    PaymentMethodEnum supports() {
        return PaymentMethodEnum.UPI;
    }
}
