package infoco.immo.usecase.payment;


import infoco.immo.ObjectTesting.appartment.ApartmentObjectTest;
import infoco.immo.ObjectTesting.payment.PaymentTestObject;
import infoco.immo.ObjectTesting.rent.RentObjectTest;
import infoco.immo.ObjectTesting.tenants.TenantsObjectTest;
import infoco.immo.configuration.BeanConfiguration;
import infoco.immo.configuration.PostgresDataConfigurationTest;
import infoco.immo.core.*;
import infoco.immo.database.SQL.appartment.ApartmentRepository;
import infoco.immo.database.SQL.payment.PaymentRepository;
import infoco.immo.database.SQL.rent.RentRepository;
import infoco.immo.database.SQL.tenant.TenantRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;
@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@ImportAutoConfiguration(exclude = PostgresDataConfigurationTest.class)
public class PaymentUseCaseTest {

    private final Payment payment = PaymentTestObject.getPayment();

    private final Apartment apartment = ApartmentObjectTest.getApartment();

    private  final Tenants tenant = TenantsObjectTest.getTenant();

    @Autowired
    JdbcTemplate jdbcTemplate;

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


    private UUID createPaymentLine() {
        PaymentLineTest paymentLineTest = new PaymentLineTest(new TenantRepository(),
                new ApartmentRepository(),
                new RentRepository(),
                RentObjectTest.getRent(),
                ApartmentObjectTest.getApartment(),
                TenantsObjectTest.getTenant());
        return paymentLineTest.createLinePayment();
    }







    @Test
    public void createTest() {
        UUID rentLine = generateRent().getId();
        payment.setTypePayment(TypePayment.CARTE);
        payment.setOrigin(Origin.PROPRIETAIRE);
        payment.setRentId(rentLine);
        beanConfiguration.paymentUseCase().create(payment);
        Assertions.assertTrue(true);
    }
    @Test
    public void getTest() {
        UUID rentLine = RentObjectTest.generateRentLine();
        payment.setTypePayment(TypePayment.CARTE);
        payment.setOrigin(Origin.PROPRIETAIRE);
        payment.setRentId(rentLine);
        paymentRepository.create(payment);
        Payment PaymentObject = beanConfiguration.paymentUseCase().get(payment.getId());
        Assertions.assertEquals(PaymentObject.getDatePayment(), payment.getDatePayment());
    }

    @Test
    public void getAllTest(){
        UUID rentLine = RentObjectTest.generateRentLine();
        payment.setTypePayment(TypePayment.CARTE);
        payment.setOrigin(Origin.PROPRIETAIRE);
        payment.setRentId(rentLine);
        paymentRepository.create(payment);
        List<Payment> listPayment = beanConfiguration.paymentUseCase().get();
        Assertions.assertTrue(listPayment.size() > 0);
    }

    @Test
    public void update(){
        Payment createObject = payment;
        UUID rentLine = RentObjectTest.generateRentLine();
        payment.setTypePayment(TypePayment.CARTE);
        payment.setOrigin(Origin.PROPRIETAIRE);
        payment.setRentId(rentLine);
        paymentRepository.create(payment);
        createObject.setId(payment.getId());
        createObject.setRentId(payment.getRentId());
        beanConfiguration.paymentUseCase().update(createObject);
        Assertions.assertTrue(true);
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
