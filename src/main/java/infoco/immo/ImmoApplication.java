package infoco.immo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class ImmoApplication {


    public static void main(String[] args) {
        SpringApplication.run(ImmoApplication.class, args);
    }

}
