package infoco.immo.http.payment;

import infoco.immo.configuration.DatabaseConfiguration;
import infoco.immo.core.Payment;
import infoco.immo.database.SQL.payment.PaymentRepository;
import infoco.immo.usecase.payment.PaymentUseCase;
import infoco.immo.usecase.payment.PaymentUseCaseI;
import infoco.immo.usecase.payment.RentReceiptData;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PaymentService implements PaymentUseCaseI {

    private final PaymentUseCase paymentRepository(){
        PaymentRepository paymentRepository = new PaymentRepository();
        paymentRepository.setDataSource(new DatabaseConfiguration().dataSource());
        return new PaymentUseCase(paymentRepository);
    }


    @Override
    public void create(Payment payment) {
        payment.setId(UUID.randomUUID());
        paymentRepository().create(payment);
    }

    @Override
    public Payment get(UUID paymentId) {
        return paymentRepository().get(paymentId);
    }

    @Override
    public void update(Payment payment) {
        paymentRepository().update(payment);
    }

    @Override
    public void  delete(UUID paymentId) {
        paymentRepository().delete(paymentId);
    }

    public List<Payment> get(){
        return  paymentRepository().get();
    }

    @Override
    public RentReceiptData generateRentReceipt(String from, String to, UUID rentId) {
        return null;
    }
}
