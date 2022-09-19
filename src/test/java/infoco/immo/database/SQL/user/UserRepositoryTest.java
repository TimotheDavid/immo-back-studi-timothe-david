package infoco.immo.database.SQL.user;


import com.github.javafaker.Faker;
import infoco.immo.configuration.PostgresDataConfigurationTest;
import infoco.immo.core.Authentication;
import infoco.immo.core.User;
import infoco.immo.database.SQL.authentication.AuthenticationRepository;
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
@ImportAutoConfiguration(exclude = PostgresDataConfigurationTest.class)
class UserRepositoryTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DataSource dataSource;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationRepository authenticationRepository;

    @Autowired
    Faker faker;
    private final User user = new User();
    @BeforeEach
    public void beforeEach(){
        userRepository.create(createUser());
    }

    @AfterEach
    public void afterEach(){
        jdbcTemplate.execute("DELETE FROM immo.authentication");
        jdbcTemplate.execute("DELETE FROM immo.users");
    }

    @Test
    void createUserTest(){
        User user = createUser();
        userRepository.create(user);
        User userObject = userRepository.get(user.getId());
        Assertions.assertNotNull(userObject);
    }

    @Test
    void getByTokenTest(){
        Authentication authentication = createAuth(user.getId());
        authenticationRepository.create(authentication);
        User user = userRepository.getByToken(authentication.getToken());
        Assertions.assertNotNull(user);
    }



    @Test
    void loginTest(){
        User userObject = userRepository.login(user);
        Assertions.assertNotNull(userObject);
    }

    @Test
    void getAuthenticationTest(){
        Authentication auth = createAuth(user.getId());
        authenticationRepository.create(auth);
        Authentication authObject = authenticationRepository.get(auth);
        Assertions.assertNotNull(authObject);
    }

    @Test
    void getAuthenticationByTokenTest(){
        Authentication auth = createAuth(user.getId());
        authenticationRepository.create(auth);
        Authentication authObject = authenticationRepository.getByToken(auth.getToken());
        Assertions.assertNotNull(authObject);
    }



    private Authentication createAuth(UUID userId){
        Authentication authentication = new Authentication();
        authentication.setUserId(userId);
        authentication.setHash(RandomStringUtils.random(10));
        authentication.setUuid(UUID.randomUUID());
        authentication.setToken(RandomStringUtils.random(64));
        authentication.setUuid(UUID.randomUUID());
        authentication.setExpires(faker.date().future(30, TimeUnit.MINUTES).toString());
        return authentication;
    }

    private User createUser(){
        user.setId(UUID.randomUUID());
        user.setName(faker.name().name());
        user.setEmail(faker.internet().emailAddress());
        user.setHash(RandomStringUtils.random(10));
        user.setPassword(faker.internet().password());
        return user;
    }


}
