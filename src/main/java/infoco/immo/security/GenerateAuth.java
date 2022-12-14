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

    private final ZonedDateTime ZONE = Instant.now().atZone(ZoneId.of("Europe/Paris"));

    private boolean decodeToken(String token, String secret){
        Algorithm algorithm = Algorithm.HMAC256(secret);
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
    private Authentication create(String secret, UUID userId) {
        String hash = RandomStringUtils.random(10, true, false);
        Instant  time = Instant.from(ZONE).plus(30,ChronoUnit.MINUTES);

        Authentication authentication = new Authentication();
        authentication.setHash(hash);
        authentication.setUuid(UUID.randomUUID());
        authentication.setToken(generateToken(hash, time, secret,userId));
        authentication.setExpires(String.valueOf(time));
        return authentication;
    }

    private String generateToken(String hash, Instant  endTime, String secret, UUID userId) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
                .withSubject(hash)
                .withSubject(String.valueOf(userId))
                .withExpiresAt(endTime)
                .sign(algorithm);
    }

    public static boolean decode(String token, String secret){
        return new GenerateAuth().decodeToken(token, secret);
    }
    public static Authentication generate(String secret, UUID userId) {
        return new GenerateAuth().create(secret, userId);
    }
}
