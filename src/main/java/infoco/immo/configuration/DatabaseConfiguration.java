package infoco.immo.configuration;

import infoco.immo.environnement.EnvironmentDefault;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;


@Configuration
@Slf4j
public class DatabaseConfiguration {

    @Autowired
    EnvironmentDefault environment;


    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(environment.databaseConnectionUrl());
        dataSource.setPassword(environment.databasePassword());
        dataSource.setUsername(environment.databaseUser());
        return dataSource;
    }

}
