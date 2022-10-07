package infoco.immo.usecase.user;

import infoco.immo.core.Authentication;
import infoco.immo.core.User;
import infoco.immo.database.SQL.user.UserRepositoryI;
import infoco.immo.http.user.HttpExceptions;
import infoco.immo.security.GenerateAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

@RequiredArgsConstructor
public class UserUseCase {

    private final  UserRepositoryI _userRepositoryI;
    private final BCryptPasswordEncoder passwordEncoder;

    private Authentication generateAuth;
    private  String secret;

    public UserUseCase(UserRepositoryI userRepository, String secret, BCryptPasswordEncoder passwordEncoder){
        this.secret = secret;
        this.passwordEncoder = passwordEncoder;
        this._userRepositoryI = userRepository;

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
        Authentication authentication = GenerateAuth.generate(secret, databaseUser.getId());
        authentication.setUserId(databaseUser.getId());

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
