package infoco.immo.security;

import infoco.immo.configuration.DatabaseConfiguration;
import infoco.immo.core.Authentication;
import infoco.immo.database.SQL.authentication.AuthenticationRepository;
import infoco.immo.database.SQL.authentication.AuthenticationRepositoryI;
import infoco.immo.database.SQL.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.List;

@Profile("dev")
@Configuration
@Order(1)
@Slf4j
public class ImmoSecurityDev  {



    private AuthenticationRepository authenticationRepository(){
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();
        authenticationRepository.setDataSource(new DatabaseConfiguration().dataSource());
        return authenticationRepository;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .cors().disable()
                .headers(httpSecurityHeadersConfigurer -> httpSecurityHeadersConfigurer.addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Origin", "*")));
        return http.build();
    }

    @Bean
    public FilterRegistrationBean<BearerAuthentication> bearerAuthFilterRegistration() {
        FilterRegistrationBean<BearerAuthentication> bearerRegistrationBean = new FilterRegistrationBean<>();
        bearerRegistrationBean.setFilter(new BearerAuthentication(authenticationRepository()));
        bearerRegistrationBean.addUrlPatterns("/api/*");
        return bearerRegistrationBean;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(List.of("HEAD", "POST", "PUT", "GET", "DELETE", "PATCH", "OPTIONS"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type", "Access-Control-Allow-Origin", "Origin"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public SecurityFilterChain authorizeAuthFilter(HttpSecurity http) throws  Exception {
        http.authorizeRequests().antMatchers( "/auth/*").permitAll();
        return http.build();
    }


}
