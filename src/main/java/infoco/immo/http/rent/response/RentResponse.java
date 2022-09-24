package infoco.immo.http.rent.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import infoco.immo.core.FromType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class RentResponse {
    private String id;
    @JsonProperty("description_in")
    private String descriptionIn;
    @JsonProperty("date_in")
    private String inDate;
    @JsonProperty("description_out")
    private String descriptionOut;
    @JsonProperty("date_out")
    private String outDate;
    private Float deposit;
    private String address;
    private String email;

}
