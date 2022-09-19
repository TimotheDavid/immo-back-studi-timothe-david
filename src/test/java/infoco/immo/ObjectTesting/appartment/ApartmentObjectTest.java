package infoco.immo.ObjectTesting.appartment;


import com.github.javafaker.Faker;
import infoco.immo.core.Apartment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;

@Component
@ActiveProfiles("test")
public class ApartmentObjectTest {


    @Test
    void test(){
        Assertions.assertTrue(true);
    }

    private final Faker faker = new Faker();

    private Apartment createApartment() {
        return Apartment.builder()
                .address(faker.address().fullAddress())
                .charge((float) faker.number().numberBetween(0, 1000))
                .city(faker.address().city())
                .complement(faker.address().buildingNumber())
                .deposit((float) faker.number().numberBetween(0, 10000))
                .rent((float ) faker.number().numberBetween(0, 1000))
                .postalCode(faker.address().countryCode())
                .build();
    }

    public static Apartment getApartment(){
        return new ApartmentObjectTest().createApartment();
    }



}
