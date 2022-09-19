package infoco.immo.database.SQL.authentication;


import infoco.immo.configuration.PostgresDataConfigurationTest;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ImportAutoConfiguration(exclude = PostgresDataConfigurationTest.class)
public class AuthenticationRepositoryTest {
}
