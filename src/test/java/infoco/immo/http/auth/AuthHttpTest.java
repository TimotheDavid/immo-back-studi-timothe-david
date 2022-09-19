package infoco.immo.http.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import infoco.immo.configuration.BeanConfiguration;
import infoco.immo.database.SQL.authentication.AuthenticationRepository;
import infoco.immo.http.user.UserService;
import infoco.immo.http.user.dto.CreateUserDTO;
import infoco.immo.http.user.dto.LoginUserDTO;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
@ComponentScan({"infoco.immo.*"})
public class AuthHttpTest {

    private final String BASE_URL = "/auth";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    JdbcTemplate jdbcTemplate;

    @MockBean
    DataSource dataSource;

    @MockBean
    BeanConfiguration beanConfiguration;

    @Autowired
    AuthenticationRepository authenticationRepository;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private UserService userService;


    @Test
    public void createUser() throws  Exception {
        CreateUserDTO createUserDTO = new CreateUserDTO();
        createUserDTO.setEmail("tim@tim.com");
        createUserDTO.setPassword("timdav");
        createUserDTO.setName("tim");
        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(createUserDTO))).andExpect(status().isCreated());
    }

    @Test
    public void login() throws Exception {
        LoginUserDTO loginUserDTO = new LoginUserDTO();
        loginUserDTO.setEmail("tim@tim.com");
        loginUserDTO.setPassword("timdav");

        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL + "/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginUserDTO)))
                .andExpect(status().isOk());

    }
}
