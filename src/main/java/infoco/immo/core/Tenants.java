package infoco.immo.core;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Tenants {
    private final String civility;
    private final String firstName;
    private final String name;
    private final String birthDate;
    private final String email;
    private final String secondEmail;
    private final String phone;
}
