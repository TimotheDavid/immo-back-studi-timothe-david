package infoco.immo;

import com.github.javafaker.Faker;
import infoco.immo.configuration.BeanConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

@SpringBootTest()
class ImmoApplicationTests {


    @Autowired
    BeanConfiguration beanConfigurationTest;


    @Bean(name =  "BeanConfigurationTest")
    BeanConfiguration beanConfiguration(){
        return beanConfigurationTest;
    }
    @Test
    void contextLoads() {
    }

    @Bean
    public Faker faker(){
        return new Faker();
    }



}
