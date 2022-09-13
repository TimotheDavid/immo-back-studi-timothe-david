package infoco.immo.http.tenant;


import com.fasterxml.jackson.databind.ObjectMapper;
import infoco.immo.ObjectTesting.tenants.TenantsObjectTest;
import infoco.immo.configuration.PostgresDataConfigurationTest;
import infoco.immo.core.Tenants;
import infoco.immo.database.SQL.tenant.TenantRepository;
import infoco.immo.http.tenant.mapper.TenantMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = TenantController.class)
@AutoConfigureMockMvc(addFilters = false)
public class TenantHttpTest {

    private final String BASE_URL = "/tenant";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TenantService tenantService;


    private TenantRepository tenantRepository(){
        TenantRepository tenantRepository = new TenantRepository();
        tenantRepository.setDataSource(new PostgresDataConfigurationTest().dataSource());
        return tenantRepository;
    }

    @Autowired
    ObjectMapper objectMapper;


    private Tenants tenants = TenantsObjectTest.getTenant();

    @Test
    public void getAllTest() throws Exception {
        mockMvc.perform(get(BASE_URL)).andExpect(status().isOk());
    }

    @Test
    public void create() throws Exception {
        String tenantJson = objectMapper.writeValueAsString(TenantMapper.INSTANCE.tenantToCreateTenantDto(tenants));
        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL).content(tenantJson).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getOne() throws Exception {
        tenants.setId(UUID.randomUUID());
        tenantRepository().create(tenants);
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/"+ tenants.getId())).andExpect(status().isOk());
    }

    @Test
    public void deleteOne() throws Exception{
        tenants.setId(UUID.randomUUID());
        tenantRepository().create(tenants);
        mockMvc.perform(MockMvcRequestBuilders.delete(BASE_URL + "/" + tenants.getId())).andExpect(status().isOk());
    }

    @Test
    public void updateOne() throws Exception {
        tenants.setId(UUID.randomUUID());
        tenantRepository().create(tenants);
        tenants.setCivility("MADAME");
        tenants.setPhone("000000000");
        String tenantJson = objectMapper.writeValueAsString(TenantMapper.INSTANCE.tenantToUpdate(tenants));
        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL).content(tenantJson).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


    }






}
