package infoco.immo.http.tenant.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import infoco.immo.core.Civility;
import lombok.*;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateTenantDTO {
    private Civility civility;
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
    private String id;

}
