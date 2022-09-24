package infoco.immo.usecase.payment;

import infoco.immo.core.Payment;
import infoco.immo.database.SQL.payment.PaymentData;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

public interface  PaymentUseCaseI {
    void  create(Payment payment);
    Payment get(UUID paymentId);
    void update(Payment payment);
    void delete(UUID paymentId);

    List<PaymentData> getPaymentData();
    InputStream generateRentReceipt(String from, String to, String tenantId) throws IOException;


}
