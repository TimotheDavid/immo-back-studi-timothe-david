package infoco.immo.http.user;


import infoco.immo.configuration.DatabaseConfiguration;
import infoco.immo.core.User;
import infoco.immo.database.SQL.authentication.AuthenticationRepository;
import infoco.immo.database.SQL.user.UserRepository;
import infoco.immo.usecase.user.Token;
import infoco.immo.usecase.user.UserNotFoundException;
import infoco.immo.usecase.user.UserUseCase;
import infoco.immo.usecase.user.UserUseCaseI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService implements UserUseCaseI {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserUseCase _userUseCase() {
        UserRepository userRepository = new UserRepository();
        userRepository.setDataSource(new DatabaseConfiguration().dataSource());
        AuthenticationRepository authenticationRepository =new AuthenticationRepository();
        authenticationRepository.setDataSource(new DatabaseConfiguration().dataSource());
        return new UserUseCase(userRepository,authenticationRepository, bCryptPasswordEncoder);
    }

    @Override
    public void create(User user) {
        _userUseCase().create(user);

    }

    public Token login(User user) throws HttpExceptions {
        try {
            return _userUseCase().login(user);
        } catch (HttpExceptions e) {
            throw new HttpExceptions("404", "not found", 404 );
        }
    }

    @Override
    public User get(UUID userId) {
        return _userUseCase().get(userId);
    }

    @Override
    public User getByToken(String token) {
        return _userUseCase().getByToken(token);
    }
}
