package infoco.immo.http.appartement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateApartmentDTO {
    private  String address;
    private  String complement;
    private  String city;
    @JsonProperty("postal_code")
    private  String postalCode;
    private  Float charge;
    private  Float rent;
    private  Float deposit;
    private String  id;
}