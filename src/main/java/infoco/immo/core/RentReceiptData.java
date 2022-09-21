package infoco.immo.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RentReceiptData {
    private Float amount;
    private Boolean sens;
    private String datePayment;

}
