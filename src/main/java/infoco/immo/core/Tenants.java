package infoco.immo.core;


import lombok.*;

import java.util.UUID;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Tenants {
    private  String  civility;
    private  String firstName;
    private  String name;
    private  String birthDate;
    private String birthPlace;
    private  String email;
    private  String secondEmail;
    private  String phone;
    private  UUID   id;
}
