package infoco.immo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class ImmoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ImmoApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder _bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
