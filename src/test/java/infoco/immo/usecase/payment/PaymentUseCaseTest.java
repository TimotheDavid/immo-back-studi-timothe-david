package infoco.immo.usecase.payment;


import infoco.immo.ObjectTesting.appartment.ApartmentObjectTest;
import infoco.immo.ObjectTesting.payment.PaymentTestObject;
import infoco.immo.ObjectTesting.rent.RentObjectTest;
import infoco.immo.ObjectTesting.tenants.TenantsObjectTest;
import infoco.immo.configuration.PostgresDataConfigurationTest;
import infoco.immo.core.Payment;
import infoco.immo.testDatabase.origin.OriginRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class PaymentUseCaseTest {

    private final  Payment payment = PaymentTestObject.getPayment();

    private final PaymentUseCase paymentUseCase = beforeAllPayment();

    private  Payment paymentTestData;

    private UUID createPaymentLine() {
        PaymentLineTest paymentLineTest = new PaymentLineTest(new TenantRepository(),
                new ApartmentRepository(),
                new RentRepository(),
                RentObjectTest.getRent(),
                ApartmentObjectTest.getApartment(),
                TenantsObjectTest.getTenant());
        return paymentLineTest.createLinePayment();
    }



    @BeforeEach
    public void beaforeEachPayment(){
        UUID lineId = createPaymentLine();
        payment.setRentId(lineId);
    }


    private OriginRepository originRepository(){
        OriginRepository originRepository = new OriginRepository();
        originRepository.setDataSource(new PostgresDataConfigurationTest().dataSource());
        return originRepository;
    }
    private static PaymentUseCase beforeAllPayment() {
        PaymentRepository paymentRepository = new PaymentRepository();
        paymentRepository.setDataSource(new PostgresDataConfigurationTest().dataSource());
        return new PaymentUseCase(paymentRepository);
    }

    @BeforeAll
    public static void beforeAll() {
        beforeAllPayment();
    }

    @Test
    public void createTest() {
        UUID OriginUuid = originRepository().get().stream().findFirst().orElseThrow().getUuid();
        payment.setOriginId(OriginUuid);
        paymentUseCase.create(payment);
        Payment paymentObject = paymentUseCase.get(payment);
        Assert.assertNotNull(paymentObject);
        Assert.assertEquals(paymentObject.getAgencyPart(), paymentObject.getAgencyPart());
    }

    @Test
    public void getTest(){
        Payment PaymentObject = paymentUseCase.get(payment);
        Assert.assertEquals(PaymentObject.getDatePayment(), payment.getDatePayment());
    }

   /* @Test
    public void updateTest(){
        Payment paymentToUpdate = payment;
        paymentUseCase.create(paymentToUpdate);
        Payment paymentData = payment;
        paymentData.setId(payment.getId());
        paymentData.setRentId(payment.getRentId());
        paymentUseCase.update(payment);
        Assert.assertNotNull(paymentObject);
        Assert.assertEquals(paymentObject.getAgencyPart(), paymentObject.getAgencyPart());


    }*/

}
