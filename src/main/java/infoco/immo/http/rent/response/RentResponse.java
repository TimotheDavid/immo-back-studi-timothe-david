package infoco.immo.http.rent.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class RentResponse {
    private String id;
    private Float amount;
    @JsonProperty("description_in")
    private String descriptionIn;
    @JsonProperty("date_in")
    private String inDate;
    @JsonProperty("description_out")
    private String descriptionOut;
    @JsonProperty("date_out")
    private String outDate;
    private Float deposit;
    @JsonProperty("agency_pourcent")
    private Float agencyPourcent;
    @JsonProperty("tenant")
    private String tenantsId;
    @JsonProperty("apartment")
    private String  apartmentId;
}
