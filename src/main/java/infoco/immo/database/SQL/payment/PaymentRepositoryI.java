package infoco.immo.database.SQL.payment;

import infoco.immo.core.Payment;
import infoco.immo.core.RentReceiptData;

import java.util.List;
import java.util.UUID;

public interface PaymentRepositoryI {
    void create(Payment payment);

    Payment get(UUID paymentId);

    List<Payment> get();
    void update(Payment payment);
    void delete(UUID paymentId);

    List<RentReceiptData> generateRentReceipt(String from, String to, String rentId);




}
