package infoco.immo.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;


@Configuration
@Slf4j
@Profile("!test")
public class DatabaseConfiguration {

    @Autowired
    DataSource dataSource;


/*    @Bean
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "applicationJDbcConnection")
    public JdbcTemplate JdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }*/

}
