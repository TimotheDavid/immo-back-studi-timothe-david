package infoco.immo.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;

@Configuration
@ActiveProfiles("test")
public class PostgresDataConfigurationTest {
/*

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUsername("test");
        dataSource.setPassword("test");
        dataSource.setUrl("jdbc:postgresql://localhost:6004/immodev");
        return dataSource;
    }
*/

}
