import java.util.List;

public class PaymentMethodFactory {
    private final List<PaymentMethod> paymentMethods;

    public PaymentMethodFactory(List<PaymentMethod> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    public PaymentMethod getMethod(PaymentMethodEnum methodEnum)
    {
   return paymentMethods.stream().filter(m->m.supports().equals(methodEnum)).findFirst().orElseThrow();
    }
}
