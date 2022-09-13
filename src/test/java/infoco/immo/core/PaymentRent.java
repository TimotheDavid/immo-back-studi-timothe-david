package infoco.immo.core;

import lombok.*;

import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentRent {

    private UUID id;
    private UUID rent;
    private UUID payment;


}
