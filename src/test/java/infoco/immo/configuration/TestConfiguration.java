package infoco.immo.configuration;


import com.github.javafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@org.springframework.boot.test.context.TestConfiguration
public class TestConfiguration {

    @Bean
    public Faker faker(){
        return new Faker();
    }

    @Bean
    public DataSource DataSourceTest(){
        return new PostgresDataConfigurationTest().dataSource();
    }

}
