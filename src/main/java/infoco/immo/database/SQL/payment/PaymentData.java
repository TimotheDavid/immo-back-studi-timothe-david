package infoco.immo.database.SQL.payment;


import infoco.immo.core.FromType;
import infoco.immo.core.Origin;
import infoco.immo.core.TypePayment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentData {
    private UUID id;
    private boolean sens;
    private TypePayment type;
    private Origin origin;
    private String firstName;
    private String username;
    private Float amount;
    private String date_payment;
    private FromType fromType;


}
