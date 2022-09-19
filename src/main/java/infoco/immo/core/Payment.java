package infoco.immo.core;

import lombok.*;

import java.util.UUID;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Payment {


    private Float amount;
    private String datePayment;
    private Float landlorPart;
    private Float agencyPart;

    private UUID rentId;
    private UUID id;
    private TypePayment typePayment;
    private Origin origin;
    private Boolean sens;
    private UUID paymentRentId;

}


