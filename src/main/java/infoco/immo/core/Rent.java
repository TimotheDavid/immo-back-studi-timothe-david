package infoco.immo.core;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rent {
    private UUID id;
    private  Float amountRent;
    private  String inDate;
    private  String descriptionIn;
    private  String outDate;
    private  String descriptionOut;
    private  Float deposit;
    private  Float agencyPourcent;
    private  UUID tenantsId;
    private  UUID apartmentId;



}
