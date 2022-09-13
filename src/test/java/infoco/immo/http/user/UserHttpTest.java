package infoco.immo.http.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import infoco.immo.http.user.dto.LoginUserDTO;
import infoco.immo.http.user.dto.CreateUserDTO;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UserController.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserHttpTest {

    private final String BASE_URL = "/user";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private UserService userService;


    @Test
    public void createUser() throws  Exception {
        CreateUserDTO createUserDTO = new CreateUserDTO();
        createUserDTO.setEmail("tim");
        createUserDTO.setPassword("timdav");
        createUserDTO.setName("tim");
        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(createUserDTO))).andExpect(status().isOk());
    }

    @Test
    public void login() throws Exception {
        LoginUserDTO loginUserDTO = new LoginUserDTO();
        loginUserDTO.setEmail("tim");
        loginUserDTO.setPassword("timdav");

        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL + "/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginUserDTO)))
                .andExpect(status().isFound());

    }
}
