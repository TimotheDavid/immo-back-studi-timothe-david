package infoco.immo.http.tenant.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTenantDTO {
    private  String  civility;
    private  String firstName;
    private  String name;
    private  String birthDate;
    private String birthPlace;
    private  String email;
    private  String secondEmail;
    private  String phone;
}
