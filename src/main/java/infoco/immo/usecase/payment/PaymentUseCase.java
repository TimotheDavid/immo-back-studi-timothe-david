package infoco.immo.usecase.payment;

import infoco.immo.core.Payment;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class PaymentUseCase {

    public final PaymentRepositoryI paymentRepositoryI;


    public void  create(Payment payment) {
        payment.setId(UUID.randomUUID());
        paymentRepositoryI.create(payment);
        paymentRepositoryI.createMappingRentPayment(payment);
    }

    public Payment get(Payment payment) {
        return paymentRepositoryI.get(payment);
    }

    public void update(Payment payment) {
        paymentRepositoryI.update(payment);
    }

    public void delete(UUID paymentId) {
        paymentRepositoryI.delete(paymentId);
    }

    public RentReceiptData generateRentReceipt(String from, String to, UUID rentId) {
        return null;
    }
}
