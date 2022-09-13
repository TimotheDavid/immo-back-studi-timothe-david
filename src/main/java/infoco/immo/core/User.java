package infoco.immo.core;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class User {
    private  UUID id;
    private  String  name;
    private  String email;
    private  String password;
    private  String hash;
    private  String expires;
    private  String token;
}
