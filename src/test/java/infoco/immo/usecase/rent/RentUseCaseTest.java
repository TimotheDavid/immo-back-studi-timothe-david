package infoco.immo.usecase.rent;


import com.github.javafaker.Faker;
import infoco.immo.ObjectTesting.appartment.ApartmentObjectTest;
import infoco.immo.ObjectTesting.payment.TestPaymentObject;
import infoco.immo.ObjectTesting.rent.RentObjectTest;
import infoco.immo.ObjectTesting.tenants.TenantsObjectTest;
import infoco.immo.configuration.BeanConfiguration;
import infoco.immo.configuration.GenerateAllDatabase;
import infoco.immo.core.*;
import infoco.immo.database.SQL.appartment.ApartmentRepository;
import infoco.immo.database.SQL.payment.PaymentData;
import infoco.immo.database.SQL.payment.PaymentRepository;
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
@RunWith(SpringRunner.class)
@ImportAutoConfiguration
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
    PaymentRepository paymentRepository;

    @Autowired
    BeanConfiguration beanConfiguration;

    @Autowired
    GenerateAllDatabase generate;

    private Faker faker = new Faker();
    private final Rent rent = RentObjectTest.getRent();

    private final Apartment apartment = ApartmentObjectTest.getApartment();

    private final Tenants  tenant = TenantsObjectTest.getTenant();

    private final Payment payment = TestPaymentObject.getPayment();

    @AfterEach
    void afterEach(){
        jdbcTemplate.execute("DELETE FROM immo.payment");
        jdbcTemplate.execute("DELETE FROM immo.rent");
        jdbcTemplate.execute("DELETE FROM immo.apartment");
        jdbcTemplate.execute("DELETE FROM immo.tenant");

    }

    @Test
    public void createTest() {
        Rent rentObject = generateRent();
        UUID rentId = beanConfiguration.rentUseCase().create(rentObject);
        Assert.assertNotNull(rentId);
    }

    @Test
    public void getTest() {
        Rent rentObject = rent;
        UUID rentId = beanConfiguration.rentUseCase().create(rentObject);

        rentObject.setId(rentId);
        Rent rentReturn = beanConfiguration.rentUseCase().get(rentObject);

        Assertions.assertEquals(rentObject.getDescriptionIn(), rentReturn.getDescriptionIn());
    }


    @Test
    public void getAll(){
        generate.generate();
        List<Rent> getAllRent = beanConfiguration.rentUseCase().get();
        Assertions.assertTrue(getAllRent.size() > 0);
    }
    @Test
    public void updateTest() {
        Rent rentObject = rent;
        rentObject.setId(UUID.randomUUID());
        rentRepository.create(rentObject);
        beanConfiguration.rentUseCase().update(rentObject);
        Rent rentUpdated = rentRepository.get(rentObject);
        Assertions.assertEquals(rentUpdated.getId(), rentObject.getId());
        Assertions.assertEquals(rentUpdated.getAmountRent(), rentObject.getAmountRent());
    }

    @Test
    public void deleteTest() {
        Rent rentObject = rent;
        rentObject.setId(UUID.randomUUID());
         rentRepository.create(rentObject);
        beanConfiguration.rentUseCase().delete(rentObject.getId());
        Assertions.assertNull(rentRepository.get(rentObject));
    }

    @Test
    public void getAllRentTenantTest(){
        generateAll();
        List<RentTenant> rentTenant = beanConfiguration.rentUseCase().getAllRentTenant();
        Assertions.assertEquals(10, rentTenant.size());
    }

    @Test
    public void getPaymentData(){
        generate.generate();
        List<PaymentData> paymentData = beanConfiguration.paymentUseCase().getPaymentData();
        Assertions.assertTrue( paymentData.size() > 0 );
    }

    @Test
    public void getDataResponseTest(){
        generateAll();
        List<RentDataResponse> rentDataResponses = beanConfiguration.rentUseCase().getDataResponse();
        Assertions.assertEquals(10, rentDataResponses.size());
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

    private void generateAll() {
        int i = 0;
        while (i < 10) {
            tenant.setId(UUID.randomUUID());
            tenant.setEmail(faker.internet().password());
            tenantRepository.create(tenant);
            apartment.setId(UUID.randomUUID());
            apartmentRepository.create(apartment);
            rent.setId(UUID.randomUUID());
            rent.setTenantsId(tenant.getId());
            rent.setApartmentId(apartment.getId());
            rentRepository.create(rent);
            payment.setId(UUID.randomUUID());
            payment.setRentId(rent.getId());
            paymentRepository.create(payment);
            i++;
        }

    }
    private void generate() {

        int i = 0;
        while( i <  10) {
            beanConfiguration.rentUseCase().create(rent);
            i++;
        }



    }


}
