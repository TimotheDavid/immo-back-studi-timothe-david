package infoco.immo.configuration;


import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

@RunWith(SpringRunner.class)
@Profile("test")
@TestConfiguration
@SpringBootTest
public class PostgresDataConfigurationTest {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUsername("test");
        dataSource.setPassword("test");
        dataSource.setUrl("jdbc:postgresql://localhost:6004/immodev");
        return dataSource;
    }

}
