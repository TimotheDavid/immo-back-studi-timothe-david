package infoco.immo.http.rent.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdateRentDTO {
    private Float amount;
    @JsonProperty(value = "in_date")
    private String inDate;
    @JsonProperty(value = "description_in")
    private String descriptionIn;
    @JsonProperty(value = "out_date")
    private String outDate;
    @JsonProperty(value = "description_out")
    private String descriptionOut;
    private Float deposit;
    @JsonProperty(value = "agency_pourcent")
    private Float agencyPourcent;
    @JsonProperty(value = "tenant")
    private String tenantsId;
    @JsonProperty(value = "apartment")
    private String apartmentId;
    private String id;
    @JsonProperty(value = "rent")
    private Float amountRent;
}
