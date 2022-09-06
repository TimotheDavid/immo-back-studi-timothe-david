package infoco.immo.core;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Rent {
    private final Float rentAmount;
    private final String inDate;
    private final String descriptionIn;
    private final String outDate;
    private final String descriptionOut;
    private final Float deposit;
    private final Float agencyPourcent;
    private final UUID tenantsId;
    private final UUID apartmentId;
}
