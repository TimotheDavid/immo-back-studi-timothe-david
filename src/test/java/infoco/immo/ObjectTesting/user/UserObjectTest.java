package infoco.immo.ObjectTesting.user;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;


@Component
@ActiveProfiles("test")
@SpringBootTest
class UserObjectTest {


    @Test
    void test(){
        Assertions.assertTrue(true);
    }
    @Autowired
    Faker faker;

    /*private User create() {
        return User.builder()
                .email(faker.internet().emailAddress())
                .expires(faker.date().future(15, TimeUnit.MINUTES).toString())
                .id(UUID.randomUUID())
                .name(faker.name().name())
                .hash(RandomStringUtils.random(10))
                .password(faker.internet().password())
                .token(RandomStringUtils.random(64))
                .build();
    }*/

   /* public static User getUser() {
        return new UserObjectTest().create();
    }*/

}
