package infoco.immo.ObjectTesting.rent;

import com.github.javafaker.Faker;
import infoco.immo.core.Rent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
@ImportAutoConfiguration
public class RentObjectTest {

    private final Faker faker = new Faker();

    @Test
    void test(){
        Assertions.assertTrue(true);
    }
    private Rent createRent() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String inDate = sdf.format(faker.date().past(100, TimeUnit.DAYS));
        String outDate = sdf.format(faker.date().future(100, TimeUnit.DAYS));
        return Rent.builder()
                .id(UUID.randomUUID())
                .deposit((float) faker.number().numberBetween(0, 10000))
                .descriptionIn(faker.lorem().paragraphs(1).toString())
                .inDate(inDate)
                .descriptionOut(faker.lorem().paragraphs(1).toString())
                .outDate(outDate)
                .descriptionInTenant(faker.lorem().paragraph(1))
                .descriptionOutTenant(faker.lorem().paragraph(1))
                .amountRent((float) faker.number().numberBetween(0, 10000))
                .agencyPourcent((float) 8).build()
                ;
    }


   public static Rent getRent() {
        return new RentObjectTest().createRent();
    }
}
