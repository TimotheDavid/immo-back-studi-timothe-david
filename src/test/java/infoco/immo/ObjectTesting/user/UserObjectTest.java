package infoco.immo.ObjectTesting.user;

import com.github.javafaker.Faker;
import infoco.immo.core.Authentication;
import infoco.immo.core.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.lang3.RandomStringUtils.*;


@Component
@ActiveProfiles("test")
@SpringBootTest
public class UserObjectTest {


    @Test
    void test(){
        Assertions.assertTrue(true);
    }

    private final Faker faker  = new Faker();

    private User create() {
        return User.builder()
                .email(faker.internet().emailAddress())
                .name(faker.name().name())
                .password(faker.internet().password())
                .build();
    }


    public static User getUser() {
        return new UserObjectTest().create();
    }

}
