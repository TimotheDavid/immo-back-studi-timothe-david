package infoco.immo.core;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class User {
    private final UUID id;
    private final String  name;
    private final String email;
    private final String password;
    private final String hash;
    private final String expires;
    private final String token;
}
