package infoco.immo.core;


import lombok.*;

import java.util.UUID;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Tenants {
    private  int  civility;
    private  String firstName;
    private  String name;
    private  String birthDate;
    private String birthPlace;
    private  String email;
    private  String secondEmail;
    private  String phone;
    private  UUID   id;

}
