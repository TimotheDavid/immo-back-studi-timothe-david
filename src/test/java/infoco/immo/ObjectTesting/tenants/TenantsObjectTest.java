package infoco.immo.ObjectTesting.tenants;

import com.github.javafaker.Faker;
import infoco.immo.core.Civility;
import infoco.immo.core.Tenants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;

import java.util.Random;


@Component
@SpringBootTest
public class TenantsObjectTest {

    private final Faker faker = new Faker();
    private Tenants createTenant() {
        Civility civility = Civility.values()[new Random().nextInt(Civility.values().length)];
        return Tenants.builder()
                .birthDate(faker.date().birthday().toString())
                .birthPlace(faker.address().fullAddress())
                .civility(civility.toString())
                .email(faker.internet().emailAddress())
                .firstName(faker.name().firstName())
                .name(faker.name().lastName())
                .phone(faker.phoneNumber().cellPhone())
                .secondEmail(faker.internet().emailAddress())
                .build();
    }


    @Test
    void test(){
        Assertions.assertTrue(true);
    }

    public static Tenants getTenant() {
        return new TenantsObjectTest().createTenant();
    }
}
