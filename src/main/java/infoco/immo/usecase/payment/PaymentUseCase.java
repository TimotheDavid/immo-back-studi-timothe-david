package infoco.immo.usecase.payment;

import infoco.immo.core.Payment;
import infoco.immo.database.SQL.payment.PaymentRepositoryI;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class PaymentUseCase {

    public final PaymentRepositoryI paymentRepositoryI;


    public void  create(Payment payment)
    {
        payment.setId(UUID.randomUUID());
        paymentRepositoryI.create(payment);
    }

    public Payment get(UUID paymentId) {
        return paymentRepositoryI.get(paymentId);
    }

    public void update(Payment payment) {
        paymentRepositoryI.update(payment);
    }

    public void  delete(UUID paymentId) {
        paymentRepositoryI.delete(paymentId);
    }

    public List<Payment> get(){
        return paymentRepositoryI.get();
    }

    public RentReceiptData generateRentReceipt(String from, String to, UUID rentId) {
        return null;
    }
}
