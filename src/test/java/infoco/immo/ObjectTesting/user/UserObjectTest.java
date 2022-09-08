package infoco.immo.ObjectTesting.user;

import com.github.javafaker.Faker;
import infoco.immo.core.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class UserObjectTest {

    @Autowired
    Faker faker;

    private User create() {
        return User.builder()
                .email(faker.internet().emailAddress())
                .expires(faker.date().future(15, TimeUnit.MINUTES).toString())
                .id(UUID.randomUUID())
                .name(faker.name().name())
                .hash(RandomStringUtils.random(10))
                .password(faker.internet().password())
                .token(RandomStringUtils.random(64))
                .build();
    }

    public static User getUser() {
        return new UserObjectTest().create();
    }

}
