package infoco.immo.ObjectTesting.payment;

import com.github.javafaker.Faker;
import infoco.immo.ObjectTesting.rent.RentObjectTest;
import infoco.immo.core.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;

import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


@SpringBootTest
@ActiveProfiles("test")
@Component
public class TestPaymentObject {

    private final Rent rent = RentObjectTest.getRent();
    private final Faker faker = new Faker();
    private  Payment create(){
        Origin origin = Origin.values()[new Random().nextInt(Origin.values().length)];
        FromType fromType = FromType.values()[new Random().nextInt(FromType.values().length)];
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        float amount = (float) faker.number().numberBetween(100, 1000);
        float agencyPart = (float) ( amount * 0.08);
        return Payment.builder()
                .agencyPart((float) ( amount * 0.08))
                .datePayment(sdf.format(faker.date().future(20, TimeUnit.DAYS)))
                .amount(amount)
                .fromType(fromType)
                .typePayment(TypePayment.CARTE)
                .rentId(UUID.randomUUID())
                .sens(faker.bool().bool())
                .landlorPart(amount - agencyPart)
                .agencyPart(agencyPart)
                .rentId(rent.getId())
                .origin(origin)
                .build();
    }


    @Test
    void test(){
        Assertions.assertTrue(true);
    }


    public static Payment getPayment(){
        return new TestPaymentObject().create();
    }
}
