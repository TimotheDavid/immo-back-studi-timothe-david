package infoco.immo.usecase.user;

import infoco.immo.core.Authentication;
import infoco.immo.core.User;
import infoco.immo.database.SQL.authentication.AuthenticationRepositoryI;
import infoco.immo.database.SQL.user.UserRepositoryI;
import infoco.immo.http.user.HttpExceptions;
import infoco.immo.security.GenerateAuth;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
public class UserUseCase {

    private final  UserRepositoryI _userRepositoryI;
    private final AuthenticationRepositoryI _authenticationRepositoryI;

    private final Authentication generateAuth = GenerateAuth.generate();
    private final PasswordEncoder passwordEncoder;

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
        Authentication authentication = GenerateAuth.generate();
        authentication.setUserId(databaseUser.getId());
        log.info(authentication.toString());
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
