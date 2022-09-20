package infoco.immo.ObjectTesting.appartment;


import com.github.javafaker.Faker;
import infoco.immo.core.Apartment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;

import javax.annotation.PostConstruct;

@Component
public class ApartmentObjectTest {


    @Test
    void test(){
        Assertions.assertTrue(true);
    }



    public Apartment createApartment() {
        Faker faker = new Faker();
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
