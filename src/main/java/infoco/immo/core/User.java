package infoco.immo.core;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class User {
    private  UUID id;
    private  String  name;
    private  String email;
    private  String password;
}
