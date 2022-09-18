package infoco.immo.http.rent;

import com.fasterxml.jackson.databind.ObjectMapper;
import infoco.immo.ObjectTesting.appartment.ApartmentObjectTest;
import infoco.immo.ObjectTesting.rent.RentDomainToCreateDTO;
import infoco.immo.ObjectTesting.rent.RentObjectTest;
import infoco.immo.ObjectTesting.tenants.TenantsObjectTest;
import infoco.immo.configuration.PostgresDataConfigurationTest;
import infoco.immo.core.Apartment;
import infoco.immo.core.Rent;
import infoco.immo.core.Tenants;
import infoco.immo.database.SQL.appartment.ApartmentRepository;
import infoco.immo.database.SQL.rent.RentRepository;
import infoco.immo.database.SQL.tenant.TenantRepository;
import infoco.immo.http.rent.dto.CreateRentDTO;
import infoco.immo.http.rent.mapper.RentMappers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest({RentController.class})
public class RentHttpTest {

    private final String BASE_URL = "/api/rent";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RentService rentService;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    RentRepository rentRepository;


    private Rent rent = RentObjectTest.getRent();

    private final Apartment apartment = ApartmentObjectTest.getApartment();

    private final Tenants tenant = TenantsObjectTest.getTenant();





/*
    @Test
    public void create() throws Exception {
        Rent rent = RentObjectTest.generateLineWitoutRent();
        CreateRentDTO createRentDTO = RentDomainToCreateDTO.createMap(rent);
        createRentDTO.setApartmentId(rent.getApartmentId().toString());
        createRentDTO.setTenantsId(rent.getTenantsId().toString());
        String dataJson = objectMapper.writeValueAsString(createRentDTO);
        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL).content(dataJson).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }*/

    @Test
    public void getOne() throws Exception {
        rentRepository.create(rent);
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/" + rent.getId())).andExpect(status().isOk());
    }

    @Test
    public void getAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL)).andExpect(status().isOk());
    }

    @Test
    public void delete() throws Exception {
        rentRepository.create(rent);
        mockMvc.perform(MockMvcRequestBuilders.delete(BASE_URL + "/" + rent.getId())).andExpect(status().isOk());
    }


}
