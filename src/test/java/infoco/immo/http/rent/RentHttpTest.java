package infoco.immo.http.rent;

import com.fasterxml.jackson.databind.ObjectMapper;
import infoco.immo.ObjectTesting.appartment.ApartmentObjectTest;
import infoco.immo.ObjectTesting.rent.RentObjectTest;
import infoco.immo.ObjectTesting.tenants.TenantsObjectTest;
import infoco.immo.configuration.BeanConfiguration;
import infoco.immo.core.Apartment;
import infoco.immo.core.Rent;
import infoco.immo.core.Tenants;
import infoco.immo.database.SQL.rent.RentRepository;
import infoco.immo.security.BearerAuthentication;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
@WebMvcTest({RentController.class})
@AutoConfigureMockMvc(addFilters = false)
@ComponentScan({
        "infoco.immo.database.SQL.*",
})
public class RentHttpTest {

    private final String BASE_URL = "/api/rent";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    RentService rentService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    RentRepository rentRepository;


    private Rent rent = RentObjectTest.getRent();

    private final Apartment apartment = ApartmentObjectTest.getApartment();

    private final Tenants tenant = TenantsObjectTest.getTenant();

    @Test
    public void getOne() throws Exception {
        rent.setId(UUID.randomUUID());
        rentRepository.create(rent);
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/" + rent.getId())).andExpect(status().isOk());
    }

    @Test
    public void getAll() throws Exception {
        rent.setId(UUID.randomUUID());
        rentRepository.create(rent);
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL)).andExpect(status().isOk());
    }

    @Test
    public void delete() throws Exception {
        rent.setId(UUID.randomUUID());
        rentRepository.create(rent);
        mockMvc.perform(MockMvcRequestBuilders.delete(BASE_URL + "/" + rent.getId())).andExpect(status().isOk());
    }


}
