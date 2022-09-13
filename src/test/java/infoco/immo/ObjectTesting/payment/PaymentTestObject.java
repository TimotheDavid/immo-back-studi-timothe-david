package infoco.immo.ObjectTesting.payment;

import com.github.javafaker.Faker;
import infoco.immo.ObjectTesting.rent.RentObjectTest;
import infoco.immo.core.Origin;
import infoco.immo.core.Payment;
import infoco.immo.core.Rent;
import infoco.immo.core.TypePayment;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class PaymentTestObject  {

    private final Rent rent = RentObjectTest.getRent();
    private final Faker faker = new Faker();
    private  Payment create(){
        float amount = (float) faker.number().numberBetween(100, 1000);
        float agencyPart = (float) ( amount * 0.08);
        return Payment.builder()
                .agencyPart((float) ( amount * 0.08))
                .datePayment(faker.date().future(20, TimeUnit.DAYS).toString())
                .amount(amount)
                .typePayment(TypePayment.CARTE)
                .rentId(UUID.randomUUID())
                .sens(faker.bool().bool())
                .landlorPart(amount - agencyPart)
                .agencyPart(agencyPart)
                .rentId(rent.getId())
                .build();
    }



    public static Payment getPayment(){
        return new PaymentTestObject().create();
    }
}
