package infoco.immo.database.payment;

import infoco.immo.core.Payment;
import infoco.immo.usecase.payment.RentReceiptData;

import java.util.UUID;

public interface PaymentRepositoryI {
    public UUID create(Payment payment);
    public Payment get(UUID paymentId);
    public void update(Payment payment);
    public UUID delete(UUID paymentId);
    public RentReceiptData generateRentReceipt(String from, String to, UUID rentId);

}
