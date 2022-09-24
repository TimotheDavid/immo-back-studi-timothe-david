package infoco.immo.http.payment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import infoco.immo.core.FromType;
import infoco.immo.core.Origin;
import infoco.immo.core.TypePayment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateDTOPayment {
    @NotNull
    @JsonProperty("payment")
    private Float  amountPayment;
    @NotNull
    @JsonProperty("date_payment")
    private String datePayment;
    @NotNull
    @JsonProperty("landlor_part")
    private Float landlorPart;
    @NotNull
    @JsonProperty("agency_part")
    private Float agencyPart;

    @NotNull
    @JsonProperty("rent_id")
    private String rentId;

    @NotNull
    @JsonProperty("type_payment")
    private TypePayment typePayment;
    @NotNull
    private Origin origin;
    @NotNull
    private Boolean sens;
    @NotNull
    @JsonProperty("payment_rent")
    private UUID paymentRentId;
    @JsonProperty("from")
    private FromType fromType;

    public Float getAmount(){
        return this.amountPayment;
    }
}
