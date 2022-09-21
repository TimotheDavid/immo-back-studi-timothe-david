package infoco.immo.database.SQL.payment;

import infoco.immo.ObjectTesting.payment.TestPaymentObject;
import infoco.immo.ObjectTesting.rent.RentObjectTest;
import infoco.immo.core.Payment;
import infoco.immo.core.Rent;
import infoco.immo.database.SQL.rent.RentRepository;
import org.junit.jupiter.api.AfterEach;
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

import javax.sql.DataSource;
import java.util.List;
import java.util.UUID;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ImportAutoConfiguration()
class PaymentRepositoryTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DataSource dataSource;

    @Autowired
    PaymentRepository paymentRepository;



    @Autowired
    RentRepository rentRepository;

    private final Rent rent = RentObjectTest.getRent();

    private final Payment payment = TestPaymentObject.getPayment();

    @BeforeEach
    public void beforeEach(){
        rent.setId(UUID.randomUUID());
        rentRepository.create(rent);
        payment.setRentId(rent.getId());
        payment.setId(UUID.randomUUID());
        paymentRepository.create(payment);
    }


    @AfterEach
    public void afterEach(){
        jdbcTemplate.execute("DELETE FROM immo.payment");
    }

    @Test
    void createTest(){
        Payment paymentObject = paymentRepository.get(payment.getId());
        Assertions.assertEquals(payment.getDatePayment(), paymentObject.getDatePayment());
    }



    @Test
    void getTest(){
        Payment paymentObject = paymentRepository.get(payment.getId());
        Assertions.assertEquals(payment.getDatePayment(), paymentObject.getDatePayment());
    }

    @Test
    void getAllTest(){
        generate();
        List<Payment> paymentList = paymentRepository.get();
        Assertions.assertEquals(10, paymentList.size());
    }

    @Test
    void updateTest(){
        payment.setAgencyPart((float)10);
        paymentRepository.update(payment);
        Payment paymentObject = paymentRepository.get(payment.getId());
        Assertions.assertEquals(payment.getAgencyPart(), paymentObject.getAgencyPart());
    }


    @Test
    void deleteTest(){
        paymentRepository.delete(payment.getId());
        Payment paymentObject = paymentRepository.get(payment.getId());
        Assertions.assertNull(paymentObject);
    }


    private void generate(){
        int i = 1;
        while (i < 10) {
            beforeEach();
            i++;
        }
    }


}
