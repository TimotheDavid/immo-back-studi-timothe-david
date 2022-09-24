package infoco.immo.http.payment.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import infoco.immo.core.FromType;
import infoco.immo.core.Origin;
import infoco.immo.core.TypePayment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDataResponse {
    private UUID id;
    private boolean sens;
    private TypePayment type;
    private Origin origin;
    @JsonProperty("first_name")
    private String firstName;
    private String username;
    private Float amount;
    private String date_payment;
    @JsonProperty("from")
    private FromType fromType;

}
