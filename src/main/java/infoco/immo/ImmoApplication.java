package infoco.immo;

import com.github.javafaker.Faker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication(scanBasePackages = {
        "infoco.immo.configuration", "infoco.immo.usecase.*", "infoco.immo.database.SQL.*", "infoco.immo.*"
},exclude = {SecurityAutoConfiguration.class}

)
public class ImmoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ImmoApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Faker faker(){
        return new Faker();
    }


}
