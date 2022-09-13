package infoco.immo.http.tenant.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("birth_date")
    private  String birthDate;
    @JsonProperty("birth_place")
    private String birthPlace;
    private  String email;
    @JsonProperty("second_email")
    private  String secondEmail;
    private  String phone;
}
