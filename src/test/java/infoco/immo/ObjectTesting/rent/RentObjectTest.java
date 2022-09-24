package infoco.immo.ObjectTesting.rent;

import com.github.javafaker.Faker;
import infoco.immo.configuration.BeanConfiguration;
import infoco.immo.core.Rent;
import infoco.immo.database.SQL.appartment.ApartmentRepository;
import infoco.immo.database.SQL.rent.RentRepository;
import infoco.immo.database.SQL.tenant.TenantRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;
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
        return Rent.builder()
                .id(UUID.randomUUID())
                .deposit((float) faker.number().numberBetween(0, 10000))
                .descriptionIn(faker.lorem().paragraphs(1).toString())
                .inDate(faker.date().past(100, TimeUnit.DAYS).toString())
                .descriptionOut(faker.lorem().paragraphs(1).toString())
                .outDate(faker.date().future(100, TimeUnit.DAYS).toString())
                .amountRent((float) faker.number().numberBetween(0, 10000))
                .agencyPourcent((float) 8).build();
    }


   public static Rent getRent() {
        return new RentObjectTest().createRent();
    }
}
