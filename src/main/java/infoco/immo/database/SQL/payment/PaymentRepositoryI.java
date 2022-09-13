package infoco.immo.database.SQL.payment;

import infoco.immo.core.Payment;
import infoco.immo.core.PaymentRent;
import infoco.immo.usecase.payment.RentReceiptData;

import java.util.List;
import java.util.UUID;

public interface PaymentRepositoryI {
    public void create(Payment payment);
    public void  createMappingRentPayment(Payment payment);

    public Payment get(Payment payment);

    public List<Payment> get();
    public void update(Payment payment);
    public void delete(UUID paymentId);

    public RentReceiptData generateRentReceipt(String from, String to, UUID rentId);

    public void mapPaymentRent(PaymentRent paymentrent);



}
