package infoco.immo.usecase.payment;

import infoco.immo.core.Payment;

import java.util.UUID;

public interface  PaymentUseCaseI {
    public void create(Payment payment);
    public Payment get(UUID paymentId);
    public void update(Payment payment);
    public void delete(UUID paymentId);
    public RentReceiptData generateRentReceipt(String from, String to, UUID rentId);


}
