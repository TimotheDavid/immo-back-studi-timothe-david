package infoco.immo.usecase.files;


import infoco.immo.configuration.BeanConfiguration;
import infoco.immo.configuration.GenerateAllDatabase;
import infoco.immo.core.RentReceiptData;
import infoco.immo.database.SQL.payment.PaymentRepository;
import infoco.immo.populate.PopulateTest;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.List;
import java.util.UUID;

@SpringBootTest
@ActiveProfiles("test")
@ImportAutoConfiguration()
public class FilesUseCaseTest {


    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    GenerateAllDatabase generate;

    @Autowired
    BeanConfiguration beanConfiguration;

    @Autowired
    PaymentRepository paymentRepository;

    @BeforeEach
    void beforeEach(){
        generate.generate();
    }

    @Test
    public void testGenerateRentReceiptTrueTest() throws IOException {
        UUID rentId = generate.generatePayment(true);
        InputStream files = beanConfiguration.filesUseCase().generateRentReceipt(rentId.toString(), "", "");
        Assertions.assertNotNull(files);
    }

    @Test
    public void testGenerateRentReceiptFalse() throws IOException {
        UUID rentId = generate.generatePayment(false);
        InputStream files = beanConfiguration.filesUseCase().generateRentReceipt(rentId.toString(), "", "");
        Assertions.assertNull(files);
    }

    @Test
    public void generateBalanceSheet() throws IOException {
        UUID rentId = generate.generatePayment(true);
        InputStream files = beanConfiguration.filesUseCase().generateBalanceSheet(rentId.toString());
        Assertions.assertNotNull(files);
    }



}
