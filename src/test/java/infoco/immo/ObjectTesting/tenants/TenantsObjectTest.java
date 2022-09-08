package infoco.immo.ObjectTesting.tenants;

import com.github.javafaker.Faker;
import infoco.immo.core.Tenants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class TenantsObjectTest {

    private final Faker faker = new Faker();

    private Tenants createTenant() {
        return Tenants.builder()
                .birthDate(faker.date().birthday().toString())
                .birthPlace(faker.address().fullAddress())
                .civility(1)
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
