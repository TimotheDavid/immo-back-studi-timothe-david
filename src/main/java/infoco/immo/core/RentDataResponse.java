package infoco.immo.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RentDataResponse {
    private UUID id;
    private Float amount;
    private String descriptionIn;
    private String inDate;
    private String descriptionOut;
    private String outDate;
    private Float deposit;
    private Float agencyPourcent;
    private String address;
    private String email;
}
