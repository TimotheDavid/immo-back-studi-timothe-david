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

    @JsonProperty(value = "amount")
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

}
