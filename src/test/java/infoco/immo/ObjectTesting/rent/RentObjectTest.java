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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
@SpringBootTest
@ActiveProfiles("test")
public class RentObjectTest {

    private final Faker faker = new Faker();

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DataSource dataSource;

    @Autowired
    BeanConfiguration beanConfiguration;
    @MockBean
    RentRepository rentRepository;

    @Autowired
    TenantRepository tenantRepository;

    @Autowired
    ApartmentRepository apartmentRepository;


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
                .amount((float) faker.number().numberBetween(0, 10000))
                .agencyPourcent((float) 8).build();
    }

/*    private UUID  generateRent() {
        Rent rentObject = createRent();
        apartment.setId(UUID.randomUUID());
        apartmentRepository.create(apartment);
        tenant.setId(UUID.randomUUID());
        tenantRepository.create(tenant);
        rentObject.setApartmentId(apartment.getId());
        rentObject.setTenantsId(tenant.getId());
        rentRepository.create(rentObject);
        return rentObject.getId();
    }*/

/*    private Rent  generateCreatedLineWthoutRent(){
        Rent rentObject = createRent();
        apartment.setId(UUID.randomUUID());
        apartmentRepository.create(apartment);
        tenant.setId(UUID.randomUUID());
        tenantRepository.create(tenant);
        rentObject.setApartmentId(apartment.getId());
        rentObject.setTenantsId(tenant.getId());
        return rentObject;
    }*/

/*    public static UUID  generateRentLine() {
        return  new RentObjectTest().generateRent();
    }*/
/*
    public static Rent generateLineWitoutRent(){ return  new RentObjectTest().generateCreatedLineWthoutRent();}*/
    public static Rent getRent() {
        return new RentObjectTest().createRent();
    }
}
