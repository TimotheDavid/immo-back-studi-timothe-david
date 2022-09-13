package infoco.immo.core;

import lombok.*;

import java.util.UUID;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Authentication {
    private UUID uuid;
    private String token;
    private String hash;
    private String expires;
    private UUID userId;
}
