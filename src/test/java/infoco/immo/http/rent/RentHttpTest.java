package infoco.immo.http.rent;

import com.fasterxml.jackson.databind.ObjectMapper;
import infoco.immo.ObjectTesting.rent.RentObjectTest;
import infoco.immo.configuration.BeanConfiguration;
import infoco.immo.core.Rent;
import infoco.immo.database.SQL.rent.RentRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.sql.DataSource;
import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
@WebMvcTest({RentController.class})
@AutoConfigureMockMvc(addFilters = false)
@ComponentScan({"infoco.immo.*"})
class RentHttpTest {

    private final String BASE_URL = "/api/rent";

    @MockBean
    JdbcTemplate jdbcTemplate;

    @MockBean
    DataSource dataSource;

    @MockBean
    BeanConfiguration beanConfiguration;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    RentService rentService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    RentRepository rentRepository;


    private final Rent rent = RentObjectTest.getRent();
    @Test
    void getOne() throws Exception {
        rent.setId(UUID.randomUUID());
        rentRepository.create(rent);
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/" + rent.getId())).andExpect(status().isOk());
    }

    @Test
    void getAll() throws Exception {
        rent.setId(UUID.randomUUID());
        rentRepository.create(rent);
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL)).andExpect(status().isOk());
    }

    @Test
    void delete() throws Exception {
        rent.setId(UUID.randomUUID());
        rentRepository.create(rent);
        mockMvc.perform(MockMvcRequestBuilders.delete(BASE_URL + "/" + rent.getId())).andExpect(status().isOk());
    }
}
