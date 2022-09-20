package infoco.immo.security;

import infoco.immo.configuration.BeanConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class ImmoSecurity {


    @Autowired
    BeanConfiguration beanConfiguration;
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedHeaders("*")
                        .allowedMethods("GET", "POST", "PATCH", "DELETE", "OPTIONS", "PUT", "HEAD");
            }
        };
    }
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .cors().disable()
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


    @Bean
    public FilterRegistrationBean<HeaderFilter> headerFilterFilterRegistration() {
        FilterRegistrationBean<HeaderFilter> headerFilterFilterRegistrationBean = new FilterRegistrationBean<>();
        headerFilterFilterRegistrationBean.setFilter(new HeaderFilter());
        headerFilterFilterRegistrationBean.addUrlPatterns("/api/*");
        return headerFilterFilterRegistrationBean;
    }


    @Bean
    public SecurityFilterChain authorizeAuthFilter(HttpSecurity http) throws  Exception {
        http.csrf().disable()
                .cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
                .and()
                .httpBasic().disable()
                .formLogin().disable()
                .authorizeRequests().antMatchers( "/auth/*").permitAll();
        return http.build();
    }






}
