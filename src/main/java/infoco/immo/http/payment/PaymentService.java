package infoco.immo.http.payment;

import infoco.immo.configuration.BeanConfiguration;
import infoco.immo.core.Payment;
import infoco.immo.usecase.payment.PaymentUseCaseI;
import infoco.immo.usecase.payment.RentReceiptData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PaymentService implements PaymentUseCaseI {

    @Autowired
    BeanConfiguration beanConfiguration;

    @Override
    public void  create(Payment payment) {
        beanConfiguration.paymentUseCase().create(payment);
    }

    @Override
    public Payment get(UUID paymentId) {
        return beanConfiguration.paymentUseCase().get(paymentId);
    }

    @Override
    public void update(Payment payment) {
        beanConfiguration.paymentUseCase().update(payment);
    }

    @Override
    public void  delete(UUID paymentId) {
        beanConfiguration.paymentUseCase().delete(paymentId);
    }

    public List<Payment> get(){
        return  beanConfiguration.paymentUseCase().get();
    }

    @Override
    public RentReceiptData generateRentReceipt(String from, String to, UUID rentId) {
        return null;
    }
}
