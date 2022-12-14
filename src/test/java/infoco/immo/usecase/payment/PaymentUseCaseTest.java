package infoco.immo.usecase.payment;


import infoco.immo.ObjectTesting.appartment.ApartmentObjectTest;
import infoco.immo.ObjectTesting.payment.TestPaymentObject;
import infoco.immo.ObjectTesting.rent.RentObjectTest;
import infoco.immo.ObjectTesting.tenants.TenantsObjectTest;
import infoco.immo.configuration.BeanConfiguration;
import infoco.immo.core.*;
import infoco.immo.database.SQL.appartment.ApartmentRepository;
import infoco.immo.database.SQL.payment.PaymentRepository;
import infoco.immo.database.SQL.rent.RentRepository;
import infoco.immo.database.SQL.tenant.TenantRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.util.List;
import java.util.Random;
import java.util.UUID;
@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@ImportAutoConfiguration
class PaymentUseCaseTest {

    private final Payment payment = TestPaymentObject.getPayment();

    private final Apartment apartment = ApartmentObjectTest.getApartment();

    private  final Tenants tenant = TenantsObjectTest.getTenant();

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DataSource dataSource;

    @Autowired
    BeanConfiguration beanConfiguration;

    @Autowired
    RentRepository rentRepository;

    @Autowired
    ApartmentRepository apartmentRepository;

    @Autowired
    TenantRepository tenantRepository;

    @Autowired
    PaymentRepository paymentRepository;

    @AfterEach
    void afterEach(){
        jdbcTemplate.execute("DELETE FROM immo.payment");
        jdbcTemplate.execute("DELETE FROM immo.rent");
        jdbcTemplate.execute("DELETE FROM immo.apartment");
        jdbcTemplate.execute("DELETE FROM immo.tenant");
    }

    @Test
    public void createTest() {
        Origin origin = Origin.values()[new Random().nextInt(Origin.values().length)];
        FromType fromType = FromType.values()[new Random().nextInt(FromType.values().length)];

        UUID rentLine = generateRent().getId();
        payment.setTypePayment(TypePayment.CARTE);
        payment.setOrigin(origin);
        payment.setRentId(rentLine);
        beanConfiguration.paymentUseCase().create(payment);
        Assertions.assertTrue(true);
    }
    @Test
    public void getTest() {
        Origin origin = Origin.values()[new Random().nextInt(Origin.values().length)];

        UUID rentLine = generateRent().getId();
        payment.setTypePayment(TypePayment.CARTE);
        payment.setOrigin(origin);
        payment.setRentId(rentLine);
        payment.setId(UUID.randomUUID());
        paymentRepository.create(payment);
        Payment PaymentObject = beanConfiguration.paymentUseCase().get(payment.getId());
        Assertions.assertEquals(PaymentObject.getDatePayment(), payment.getDatePayment());
    }

    @Test
    public void getAllTest(){
        Origin origin = Origin.values()[new Random().nextInt(Origin.values().length)];

        UUID rentLine = generateRent().getId();
        payment.setTypePayment(TypePayment.CARTE);
        payment.setOrigin(origin);
        payment.setRentId(rentLine);
        payment.setId(UUID.randomUUID());
        paymentRepository.create(payment);
        List<Payment> listPayment = beanConfiguration.paymentUseCase().get();
        Assertions.assertTrue(listPayment.size() > 0);
    }

    @Test
    public void updateTest(){
        Origin origin = Origin.values()[new Random().nextInt(Origin.values().length)];

        Payment createObject = payment;
        UUID rentLine = generateRent().getId();
        payment.setTypePayment(TypePayment.CARTE);
        payment.setOrigin(origin);
        payment.setRentId(rentLine);
        payment.setId(UUID.randomUUID());
        paymentRepository.create(payment);
        createObject.setId(payment.getId());
        createObject.setRentId(payment.getRentId());
        beanConfiguration.paymentUseCase().update(createObject);
        Assertions.assertTrue(true);
    }

    @Test
    public void deleteTest(){
        Origin origin = Origin.values()[new Random().nextInt(Origin.values().length)];

        UUID rentLine = generateRent().getId();
        payment.setTypePayment(TypePayment.CARTE);
        payment.setOrigin(origin);
        payment.setRentId(rentLine);
        payment.setId(UUID.randomUUID());
        paymentRepository.create(payment);
        beanConfiguration.paymentUseCase().delete(payment.getId());
        Payment paymentObject = paymentRepository.get(payment.getId());
        Assertions.assertNull(paymentObject);
    }



    private Rent generateRent() {
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


}
