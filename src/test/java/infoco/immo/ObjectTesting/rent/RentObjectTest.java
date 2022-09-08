package infoco.immo.ObjectTesting.rent;

import com.github.javafaker.Faker;
import infoco.immo.ObjectTesting.appartment.ApartmentObjectTest;
import infoco.immo.ObjectTesting.tenants.TenantsObjectTest;
import infoco.immo.core.Apartment;
import infoco.immo.core.Rent;
import infoco.immo.core.Tenants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RentObjectTest {

   private final Faker faker = new Faker();

    private final Apartment apartment = ApartmentObjectTest.getApartment();
    private final Tenants tenant = TenantsObjectTest.getTenant();

    private Rent createRent() {
        return Rent.builder()
                .apartmentId(apartment.getId())
                .deposit((float) faker.number().numberBetween(0, 10000))
                .descriptionIn(faker.lorem().paragraphs(1).toString())
                .inDate(faker.date().past(100, TimeUnit.DAYS).toString())
                .descriptionOut(faker.lorem().paragraphs(1).toString())
                .outDate(faker.date().future(100, TimeUnit.DAYS).toString())
                .rentAmount((float) faker.number().numberBetween(0, 10000))
                .agencyPourcent((float) 8)
                .tenantsId(tenant.getId()).build();
    }

    public static Rent getRent() {
        return new RentObjectTest().createRent();
    }
}
