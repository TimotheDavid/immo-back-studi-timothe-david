package infoco.immo.usecase.user;

import com.github.javafaker.Faker;
import infoco.immo.configuration.BeanConfiguration;
import infoco.immo.core.Authentication;
import infoco.immo.core.User;
import infoco.immo.database.SQL.authentication.AuthenticationRepository;
import infoco.immo.database.SQL.user.UserRepository;
import infoco.immo.http.user.HttpExceptions;
import org.apache.commons.lang3.RandomStringUtils;
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
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ImportAutoConfiguration
class UserUseCaseTest {

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    Faker faker;

    @Autowired
    BeanConfiguration beanConfiguration;


    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationRepository authenticationRepository;

    private final User user = new User();


    @BeforeEach
    void beforeEach() {
        user.setPassword(faker.internet().password());
        user.setName(faker.name().name());
        user.setEmail(faker.internet().emailAddress());
        user.setId(UUID.randomUUID());
        userRepository.create(user);
    }

    @AfterEach
    void afterEach(){
        jdbcTemplate.execute("DELETE FROM immo.authentication");
        jdbcTemplate.execute("DELETE FROM immo.users");

    }

    private Authentication createAuthentication(UUID userId) {
        Authentication authentication = new Authentication();
        authentication.setUuid(UUID.randomUUID());
        authentication.setExpires(faker.date().future(30, TimeUnit.MINUTES).toString());
        authentication.setUserId(userId);
        authentication.setHash(RandomStringUtils.random(10));
        authentication.setToken(RandomStringUtils.random(64));
        return authentication;


    }


    @Test
    void createTest() {
        beanConfiguration.userUseCase().create(user);
        Assertions.assertTrue(true);
    }

    @Test
    void loginTest() throws HttpExceptions {
        Token token = beanConfiguration.userUseCase().login(user);
        Assertions.assertNotNull(token);
    }

    @Test
    void getUserByIdTest() {
        User userObject = beanConfiguration.userUseCase().get(user.getId());
        Assertions.assertEquals(user.getEmail(), userObject.getEmail());
    }

    @Test
    void getUserByTokenTest() {
        Authentication authentication = createAuthentication(user.getId());
        authenticationRepository.create(authentication);
        User userToken = beanConfiguration.userUseCase().getByToken(authentication.getToken());
        Assertions.assertEquals(authentication.getUserId(), userToken.getId());


    }


}
