package infoco.immo.http.rent.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateRentDTO {

    @JsonProperty(value = "rent")
    private Float amountRent;
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
    @JsonProperty(value = "email")
    private String  emailTenant;
    @JsonProperty(value = "address")
    private String apartmentAddress;
    @JsonProperty("tenant")
    private String tenantId;
    @JsonProperty("apartment")
    private String apartmentId;
    @JsonProperty("description_out_tenant")
    private String descriptionOutTenant;
    @JsonProperty("description_in_tenant")
    private String descriptionInTenant;

    public void setAmountRent(Float amountRent) {
        this.amountRent = amountRent;
    }
}
