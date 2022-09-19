package infoco.immo.http.user;


import infoco.immo.configuration.BeanConfiguration;
import infoco.immo.core.User;
import infoco.immo.usecase.user.Token;
import infoco.immo.usecase.user.UserUseCaseI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService implements UserUseCaseI {

    @Autowired
    BeanConfiguration beanConfiguration;

    @Override
    public void create(User user) {
        beanConfiguration.userUseCase().create(user);

    }

    public Token login(User user) throws HttpExceptions {
        try {
            return beanConfiguration.userUseCase().login(user);
        } catch (HttpExceptions e) {
            throw new HttpExceptions("404", "not found", 404 );
        }
    }

    @Override
    public User get(UUID userId) {
        return beanConfiguration.userUseCase().get(userId);
    }

    @Override
    public User getByToken(String token) {
        return beanConfiguration.userUseCase().getByToken(token);
    }
}
