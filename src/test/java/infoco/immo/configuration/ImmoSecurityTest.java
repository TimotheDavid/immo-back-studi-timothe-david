package infoco.immo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@TestConfiguration
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
public class ImmoSecurityTest  {


    @Autowired
    BeanConfiguration beanConfiguration;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws  Exception {
        http.csrf().disable()
                .formLogin().disable()
                .httpBasic().disable();
        return http.build();
    }



}