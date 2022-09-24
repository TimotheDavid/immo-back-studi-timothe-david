package infoco.immo.populate;

import infoco.immo.ObjectTesting.appartment.ApartmentObjectTest;
import infoco.immo.ObjectTesting.payment.TestPaymentObject;
import infoco.immo.ObjectTesting.rent.RentObjectTest;
import infoco.immo.ObjectTesting.tenants.TenantsObjectTest;
import infoco.immo.configuration.GenerateAllDatabase;
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
@ImportAutoConfiguration
public class PopulateTest {


    @Autowired
    GenerateAllDatabase generate;

    @Test
    void populateDatabaseTest(){
        generate.generate();
        Assertions.assertTrue(true);
    }
}
