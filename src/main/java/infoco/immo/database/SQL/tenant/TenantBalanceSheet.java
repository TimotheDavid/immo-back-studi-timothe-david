package infoco.immo.database.SQL.tenant;

import infoco.immo.core.FromType;
import infoco.immo.core.Origin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TenantBalanceSheet {

    private Float amount;
    private Boolean sens;
    private FromType fromType;
    private String datePayment;
    private Origin origin;
    private Float rent;
}
