package infoco.immo.http.tenant.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TenantResponse {
    private String id;
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
