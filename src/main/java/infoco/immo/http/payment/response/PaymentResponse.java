package infoco.immo.http.payment.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import infoco.immo.core.Origin;
import infoco.immo.core.TypePayment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponse {

    private String id;
    private Float amount;

    @JsonProperty("date_payment")
    private String datePayment;

    @JsonProperty("landlor_part")
    private Float landlorPart;

    @JsonProperty("agency_part")
    private Float agencyPart;

    @JsonProperty("type_payment")
    private TypePayment typePayment;

    private Origin origin;

    private Boolean sens;


}
