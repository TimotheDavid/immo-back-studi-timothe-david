package infoco.immo.usecase.rent;


import infoco.immo.ObjectTesting.appartment.ApartmentObjectTest;
import infoco.immo.ObjectTesting.rent.RentObjectTest;
import infoco.immo.ObjectTesting.tenants.TenantsObjectTest;
import infoco.immo.configuration.BeanConfiguration;
import infoco.immo.configuration.PostgresDataConfigurationTest;
import infoco.immo.core.Apartment;
import infoco.immo.core.Rent;
import infoco.immo.core.Tenants;
import infoco.immo.database.SQL.appartment.ApartmentRepository;
import infoco.immo.database.SQL.rent.RentRepository;
import infoco.immo.database.SQL.tenant.TenantRepository;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.util.List;
import java.util.UUID;


@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@ImportAutoConfiguration(exclude = { PostgresDataConfigurationTest.class})
class RentUseCaseTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DataSource dataSource;

    @Autowired
    RentRepository rentRepository;

    @Autowired
    TenantRepository tenantRepository;

    @Autowired
    ApartmentRepository apartmentRepository;

    @Autowired
    BeanConfiguration beanConfiguration;
    private final Rent rent = RentObjectTest.getRent();

    private final Apartment apartment = ApartmentObjectTest.getApartment();

    private final Tenants  tenant = TenantsObjectTest.getTenant();

    @AfterEach
    void afterEach(){
        jdbcTemplate.execute("DELETE FROM immo.payment_rent");
        jdbcTemplate.execute("DELETE FROM immo.tenant");
        jdbcTemplate.execute("DELETE FROM immo.apartment");
        jdbcTemplate.execute("DELETE FROM immo.rent");

    }

    @Test
    void createTest() {
        Rent rentObject = generateRent();
        UUID rentId = beanConfiguration.rentUseCase().create(rentObject);
        Assert.assertNotNull(rentId);
    }

    @Test
    void getTest() {
        Rent rentObject = rent;
        UUID rentId = beanConfiguration.rentUseCase().create(rentObject);

        rentObject.setId(rentId);
        Rent rentReturn = beanConfiguration.rentUseCase().get(rentObject);

        Assertions.assertEquals(rentObject.getDescriptionIn(), rentReturn.getDescriptionIn());
    }


    @Test
    void getAll(){
        generate();
        List<Rent> getAllRent = beanConfiguration.rentUseCase().get();
        Assertions.assertTrue(getAllRent.size() > 0);

    }
    @Test
    void updateTest() {
        Rent rentObject = rent;
        rentObject.setId(UUID.randomUUID());
        rentRepository.create(rentObject);
        beanConfiguration.rentUseCase().update(rentObject);
        Rent rentUpdated = rentRepository.get(rentObject);
        Assertions.assertEquals(rentUpdated.getId(), rentObject.getId());
        Assertions.assertEquals(rentUpdated.getAmount(), rentObject.getAmount());
    }

    @Test
    void deleteTest() {
        Rent rentObject = rent;
        rentObject.setId(UUID.randomUUID());
         rentRepository.create(rentObject);
        beanConfiguration.rentUseCase().delete(rentObject.getId());
        Assertions.assertNull(rentRepository.get(rentObject));
    }

    private Rent  generateRent() {
        Rent rentObject = RentObjectTest.getRent();
        apartment.setId(UUID.randomUUID());
        apartmentRepository.create(apartment);
        tenant.setId(UUID.randomUUID());
        tenantRepository.create(tenant);
        rentObject.setApartmentId(apartment.getId());
        rentObject.setTenantsId(tenant.getId());
        rentRepository.create(rentObject);
        return rentObject;
    }

    private void generate() {

        int i = 0;
        while( i <  10) {
            beanConfiguration.rentUseCase().create(rent);
            i++;
        }



    }


}
