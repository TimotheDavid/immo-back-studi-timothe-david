package infoco.immo.usecase.user;

import infoco.immo.core.Authentication;
import infoco.immo.core.User;
import infoco.immo.database.SQL.authentication.AuthenticationRepository;
import infoco.immo.database.SQL.authentication.AuthenticationRepositoryI;
import infoco.immo.database.SQL.user.UserRepository;
import infoco.immo.database.SQL.user.UserRepositoryI;
import infoco.immo.http.user.HttpExceptions;
import infoco.immo.security.GenerateAuth;
import infoco.immo.usecase.payment.PaymentUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

@RequiredArgsConstructor
public class UserUseCase {

    private final  UserRepositoryI _userRepositoryI;
    private final AuthenticationRepositoryI _authenticationRepositoryI;
    private final BCryptPasswordEncoder passwordEncoder;

    private Authentication generateAuth;
    private  String secret;

    public UserUseCase(UserRepositoryI userRepository, AuthenticationRepositoryI authenticationRepositoryI, String secret, BCryptPasswordEncoder passwordEncoder){
        this.secret = secret;
        this.passwordEncoder = passwordEncoder;
        this._userRepositoryI = userRepository;
        this._authenticationRepositoryI = authenticationRepositoryI;
        generateAuth = GenerateAuth.generate(secret);

    }



    public void  create(User user) {
        user.setId(UUID.randomUUID());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        _userRepositoryI.create(user);
    }


    public Token login(User user) throws HttpExceptions {
        User databaseUser;
        try{
            databaseUser =  _userRepositoryI.login(user);
            passwordEncoder.matches(user.getPassword(),databaseUser.getPassword());
        }catch (Exception exception){
            throw new HttpExceptions(HttpStatus.NOT_FOUND.toString(), HttpStatus.NOT_FOUND.toString(), HttpStatus.NOT_FOUND.value());
        }
        Authentication authentication = GenerateAuth.generate(secret);
        authentication.setUserId(databaseUser.getId());
        _authenticationRepositoryI.create(authentication);

        Token signingtoken = new Token();
        signingtoken.setToken(authentication.getToken());
        return  signingtoken;
    }

    public User get(UUID userId) {
        return _userRepositoryI.get(userId);
    }

    ;

    public User getByToken(String token) {
        return _userRepositoryI.getByToken(token);
    }



    ;


}
