package infoco.immo.populate;

import infoco.immo.ObjectTesting.appartment.ApartmentObjectTest;
import infoco.immo.ObjectTesting.payment.TestPaymentObject;
import infoco.immo.ObjectTesting.rent.RentObjectTest;
import infoco.immo.ObjectTesting.tenants.TenantsObjectTest;
import infoco.immo.core.Apartment;
import infoco.immo.core.Payment;
import infoco.immo.core.Rent;
import infoco.immo.core.Tenants;
import infoco.immo.database.SQL.appartment.ApartmentRepository;
import infoco.immo.database.SQL.payment.PaymentRepository;
import infoco.immo.database.SQL.rent.RentRepository;
import infoco.immo.database.SQL.tenant.TenantRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureJdbc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.util.UUID;

@SpringBootTest
@ActiveProfiles("populate")
@AutoConfigureJdbc
@RunWith(SpringJUnit4ClassRunner.class)
@ImportAutoConfiguration
class PopulateTest {


    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DataSource dataSource;

    @Autowired
    RentRepository rentRepository;

    @Autowired
    ApartmentRepository apartmentRepository;

    @Autowired
    TenantRepository tenantRepository;

    @Autowired
    PaymentRepository paymentRepository;
    private void generate(){
        int i = 1;
        while (i < 20) {
            UUID apartmentId = generateApartment();
            UUID tenantId = generateTenant();
            UUID rentId = generateRent(tenantId, apartmentId);
            generateRentPayment(rentId);
            i++;
        }
    }
    private UUID  generateRent(UUID tenantId, UUID apartmentId){
        Rent rent = RentObjectTest.getRent();
        rent.setId(UUID.randomUUID());
        rent.setApartmentId(apartmentId);
        rent.setTenantsId(tenantId);
        rentRepository.create(rent);
        return rent.getId();

    }


    private UUID generateTenant(){
        Tenants tenants = TenantsObjectTest.getTenant();
        tenants.setId(UUID.randomUUID());
        tenantRepository.create(tenants);
        return tenants.getId();
    }

    private void generateRentPayment(UUID rentId) {

        int i = 0;
        while(i < 10){
            Payment payment = TestPaymentObject.getPayment();
            payment.setRentId(rentId);
            payment.setId(UUID.randomUUID());
            paymentRepository.create(payment);
            i++;
        }

    }


    private UUID  generateApartment(){
        Apartment apartment = ApartmentObjectTest.getApartment();
        apartment.setId(UUID.randomUUID());
        apartmentRepository.create(apartment);
        return apartment.getId();
    }

    private UUID generatePayment(){
        Payment payment =  TestPaymentObject.getPayment();
        payment.setId(UUID.randomUUID());
        paymentRepository.create(payment);
        return payment.getId();
    }

    @Test
    void populateDatabaseTest(){
        generate();
        Assertions.assertTrue(true);
    }
}
