package infoco.immo.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import infoco.immo.core.Authentication;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;

import javax.validation.constraints.Min;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

public class GenerateAuth {

    private final String SECRET = "secret";
    private final ZonedDateTime ZONE = Instant.now().atZone(ZoneId.of("Europe/Paris"));

    private boolean decodeToken(String token, String hash){
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        JWTVerifier jwtVerifier = JWT.require(algorithm)
              //  .withSubject(hash)
                .build();
        DecodedJWT decodedJWT;
        try{
            decodedJWT =  jwtVerifier.verify(token);
        }catch (JWTVerificationException exception){
            return false;
        }

        ZonedDateTime  tokenExpiration = decodedJWT.getExpiresAt().toInstant().atZone(ZoneId.of("Europe/Paris"));
        return tokenExpiration.compareTo(ZONE) > 0;
    }
    private Authentication create() {
        String hash = RandomStringUtils.random(10, true, false);
        Instant  time = Instant.from(ZONE).plus(30,ChronoUnit.MINUTES);

        Authentication authentication = new Authentication();
        authentication.setHash(hash);
        authentication.setUuid(UUID.randomUUID());
        authentication.setToken(generateToken(hash, time));
        authentication.setExpires(String.valueOf(time));
        return authentication;
    }

    private String generateToken(String hash, Instant  endTime) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        return JWT.create()
                .withSubject(hash)
                .withExpiresAt(endTime)
                .sign(algorithm);
    }

    public static boolean decode(String token, String hash){
        return new GenerateAuth().decodeToken(token, hash);
    }
    public static Authentication generate() {
        return new GenerateAuth().create();
    }
}
