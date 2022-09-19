package infoco.immo.http.payment;


import com.fasterxml.jackson.databind.ObjectMapper;
import infoco.immo.ObjectTesting.payment.TestPaymentObject;
import infoco.immo.configuration.BeanConfiguration;
import infoco.immo.core.Payment;
import infoco.immo.database.SQL.payment.PaymentRepository;
import infoco.immo.http.payment.mapper.PaymentMappers;
import infoco.immo.http.user.UserService;
import org.junit.After;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.sql.DataSource;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = PaymentController.class)
@AutoConfigureMockMvc(addFilters = false)
@ComponentScan({"infoco.immo.*"})
public class PaymentHttpTest {

    private final String BASE_URL = "/api/rent";

    @MockBean
    JdbcTemplate jdbcTemplate;

    @MockBean
    DataSource dataSource;

    @Autowired
    BeanConfiguration beanConfiguration;

    @MockBean
    UserService userService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    PaymentRepository paymentRepository;

    @MockBean
    PaymentService paymentService;

    @Autowired
    private MockMvc mockMvc;



    private final Payment payment = TestPaymentObject.getPayment();

    @After
    public void afterEach(){
        jdbcTemplate.execute("DELETE FROM immo.payment");
        jdbcTemplate.execute("DELETE FROM immo.payment_rent");
        jdbcTemplate.execute("DELETE FROM immo.rent");
    }

    @Before
    public void beforeEach(){
        payment.setId(UUID.randomUUID());
        paymentRepository.create(payment);
    }
    @Test
    public void createTest() throws Exception {
        String dataJson = objectMapper.writeValueAsString(PaymentMappers.INSTANCE.domaineToCreateDTO(payment));
        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL).content(dataJson).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void getAllTest() throws Exception {
        generate();
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL)).andExpect(status().isOk());
    }

    @Test
    public void getTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/" + payment.getId().toString())).andExpect(status().isOk());
    }

    @Test
    public void updateTest() throws Exception {
        payment.setAgencyPart((float) 10);
        String dataJson = objectMapper.writeValueAsString(PaymentMappers.INSTANCE.domaineToUpdateDTO(payment));
        mockMvc.perform(MockMvcRequestBuilders.put(BASE_URL).contentType(MediaType.APPLICATION_JSON).content(dataJson)).andExpect(status().isOk());
    }

    @Test
    public void deleteTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(BASE_URL + "/" + payment.getId()).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

    }

    private void generate(){
        int i = 0;
        while (i < 10){
            beforeEach();
            i++;
        }
    }

}
