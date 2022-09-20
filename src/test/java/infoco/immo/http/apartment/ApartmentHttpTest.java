package infoco.immo.http.apartment;


import com.fasterxml.jackson.databind.ObjectMapper;
import infoco.immo.ObjectTesting.appartment.ApartmentObjectTest;
import infoco.immo.configuration.BeanConfiguration;
import infoco.immo.core.Apartment;
import infoco.immo.database.SQL.appartment.ApartmentRepository;
import infoco.immo.http.appartement.ApartmentController;
import infoco.immo.http.appartement.AppartmentService;
import infoco.immo.http.appartement.apartmentMapper.ApartmentMapper;
import org.junit.Test;
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

import javax.sql.DataSource;
import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ApartmentController.class)
@AutoConfigureMockMvc(addFilters = false)
@ComponentScan({
      "infoco.immo.*"
})
public class ApartmentHttpTest {

    private final String BASE_URL = "/api/apartment";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    AppartmentService appartmentService;

    @MockBean
    JdbcTemplate jdbcTemplate;

    @MockBean
    DataSource dataSource;

    @MockBean
    BeanConfiguration beanConfiguration;



    @Autowired
    ApartmentRepository apartmentRepository;
    @Autowired
    ObjectMapper objectMapper;

    private final Apartment apartment = ApartmentObjectTest.getApartment();




    @Test
    public void createApartment() throws Exception {
        apartment.setId(UUID.randomUUID());
        String dataJson = objectMapper.writeValueAsString(ApartmentMapper.INSTANCE.apartmentTOcreateDTO(apartment));
        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL).content(dataJson).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void getOneApartment() throws Exception {
        apartment.setId(UUID.randomUUID());
        apartmentRepository.create(apartment);
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/" + apartment.getId())).andExpect(status().isOk());
    }

    @Test
    public void update() throws  Exception {
        apartment.setId(UUID.randomUUID());
        apartmentRepository.create(apartment);
        apartment.setDeposit((float) 10000000.00);
        String dataJson = objectMapper.writeValueAsString(ApartmentMapper.INSTANCE.updateToApartment(apartment));
        mockMvc.perform(MockMvcRequestBuilders.put(BASE_URL).content(dataJson).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void getAll() throws  Exception {
        generate();
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL)).andExpect(status().isOk());

    }


    @Test
    public void delete() throws  Exception {
        apartment.setId(UUID.randomUUID());
        apartmentRepository.create(apartment);
        mockMvc.perform(MockMvcRequestBuilders.delete(BASE_URL + "/" + apartment.getId())).andExpect(status().isOk());
    }

    private void generate(){
        int number = 0;
        while( number < 10) {
            number++;
            apartment.setId(UUID.randomUUID());
            apartmentRepository.create(apartment);
        }
    }
}
