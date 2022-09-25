package infoco.immo.security;

import infoco.immo.database.SQL.authentication.AuthenticationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;
import java.util.List;

@Profile(value = {"dev", "prod", "debug"})
@Configuration
@Slf4j
public class ImmoSecurity {

    @Autowired
    HttpSecurity httpSecurity;


    @Autowired
    AuthenticationRepository authenticationRepository;

    @Autowired
    Environment environment;

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        log.info(environment.getProperty("allowed.origin"));
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of(environment.getProperty("allowed.origin")));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PATCH", "PUT", "DELETE", "OPTIONS", "HEAD"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("Authorization","Content-Type", "Allowed-Origins"));
        configuration.setExposedHeaders(List.of("X-Get-Header"));
        configuration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }



    @Bean
    public FilterRegistrationBean<HeaderFilter> headerFilterFilterRegistration() {
        FilterRegistrationBean<HeaderFilter> headerFilterFilterRegistrationBean = new FilterRegistrationBean<>();
        headerFilterFilterRegistrationBean.setFilter(new HeaderFilter());
        headerFilterFilterRegistrationBean.addUrlPatterns("/api/*");
        return headerFilterFilterRegistrationBean;
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .cors().and()
                .authorizeRequests().antMatchers("/api/**").permitAll()
                .and()
                .authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/api/**").permitAll()
                .and()
                .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .headers(httpSecurityHeadersConfigurer -> httpSecurityHeadersConfigurer.addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Origin", "*")));
        return http.build();
    }

    @Bean
    public FilterRegistrationBean<BearerAuthentication> bearerAuthFilterRegistration() {
        FilterRegistrationBean<BearerAuthentication> bearerRegistrationBean = new FilterRegistrationBean<>();
        bearerRegistrationBean.setFilter(new BearerAuthentication());
        bearerRegistrationBean.addUrlPatterns("/api/*");
        return bearerRegistrationBean;
    }

}
