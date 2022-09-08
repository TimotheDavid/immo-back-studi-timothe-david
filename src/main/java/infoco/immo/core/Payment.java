package infoco.immo.core;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Payment {
    private Float amount;
    private String datePayment;
    private Float landlorPart;
    private Float agencyPart;
    private UUID rentId;
    private UUID id;
    private UUID typePayment;
    private UUID   originId;
}
