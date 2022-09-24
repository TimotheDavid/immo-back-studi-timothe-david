package infoco.immo.configuration;

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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.util.UUID;

@Component
@ImportAutoConfiguration
public class GenerateAllDatabase {

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

    public void generate(){
        int i = 1;
        while (i < 2) {
            UUID apartmentId = generateApartment();
            UUID tenantId = generateTenant();
            UUID rentId = generateRent(tenantId, apartmentId);
            generateOkPayment(rentId);
            generateBadPayment(rentId);
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

    private UUID  generateApartment(){
        Apartment apartment = ApartmentObjectTest.getApartment();
        apartment.setId(UUID.randomUUID());
        apartmentRepository.create(apartment);
        return apartment.getId();
    }

    public UUID generatePayment(boolean type) {
        UUID apartmentId = generateApartment();
        UUID tenantId = generateTenant();
        UUID rentId = generateRent(tenantId, apartmentId);
        if (type) {
            generateOkPayment(rentId);
        } else {
            generateBadPayment(rentId);
        }
        return rentId;
    }
    private void  generateBadPayment(UUID rentId) {
        int i = 0;
        while (i < 2) {
            Payment payment = TestPaymentObject.getPayment();
            payment.setId(UUID.randomUUID());
            payment.setSens(false);
            payment.setRentId(rentId);
            paymentRepository.create(payment);
            i++;
        }
    }


    private void  generateOkPayment(UUID rentId) {
        int i = 0;
        while (i < 10) {
            Payment payment = TestPaymentObject.getPayment();
            payment.setId(UUID.randomUUID());
            payment.setSens(true);
            payment.setRentId(rentId);
            paymentRepository.create(payment);
            i++;
        }
    }
    private UUID generatePayment(){
        Payment payment =  TestPaymentObject.getPayment();
        payment.setId(UUID.randomUUID());
        paymentRepository.create(payment);
        return payment.getId();
    }

}
