package infoco.immo.usecase.payment;


import infoco.immo.core.Payment;
import infoco.immo.core.PaymentRent;
import infoco.immo.database.SQL.payment.PaymentRepositoryI;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class PaymentUseCase {

    public final  PaymentRepositoryI paymentRepositoryI;


    public void create(Payment payment) {
        payment.setId(UUID.randomUUID());
        paymentRepositoryI.create(payment);
        PaymentRent paymentRent = PaymentRent.builder().
                rent(payment.getRentId()).
                payment(payment.getId()).
                id(UUID.randomUUID()).build();
        paymentRepositoryI.mapPaymentRent(paymentRent);

    }

    public Payment get(Payment payment){
        return paymentRepositoryI.get(payment);
    }

    public List<Payment> get(){
        return paymentRepositoryI.get();
    }

    public void update(Payment payment){
        paymentRepositoryI.update(payment);
    }

    public void delete(UUID paymentId){
        paymentRepositoryI.delete(paymentId);
    }






}
