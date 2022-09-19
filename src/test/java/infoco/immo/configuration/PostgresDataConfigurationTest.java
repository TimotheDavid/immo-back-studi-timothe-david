package infoco.immo.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;

@Configuration
@Profile("http-test")
public class PostgresDataConfigurationTest {


   /* @Bean
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "applicationJDbcConnection")
    public JdbcTemplate JdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }
*/
}
