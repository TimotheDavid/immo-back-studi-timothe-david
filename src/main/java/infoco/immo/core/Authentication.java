package infoco.immo.core;

import lombok.*;

import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
public class Authentication {
    private UUID uuid;
    private String token;
    private String hash;
    private String expires;
    private UUID userId;
}
