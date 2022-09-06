package infoco.immo.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class Payment {
    private final Float  amount;
    private final String datePayment;
    private final Float  landlorPart;
    private final Float  agencyPart;
    private final UUID    rentId;
}
