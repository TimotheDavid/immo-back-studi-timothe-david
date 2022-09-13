package infoco.immo.ObjectTesting.tenants;

import com.github.javafaker.Faker;
import infoco.immo.core.Civility;
import infoco.immo.core.Tenants;
import org.springframework.stereotype.Component;

import java.util.Random;


@Component
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

    public static Tenants getTenant() {
        return new TenantsObjectTest().createTenant();
    }
}
