package infoco.immo.usecase.payment;

import infoco.immo.core.Payment;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

public interface  PaymentUseCaseI {
    void  create(Payment payment);
    Payment get(UUID paymentId);
    void update(Payment payment);
    void delete(UUID paymentId);
    InputStream generateRentReceipt(String from, String to, String tenantId) throws IOException;


}
