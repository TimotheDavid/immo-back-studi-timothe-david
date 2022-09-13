package infoco.immo.core;


import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Apartment {
    private UUID id;
    private  String address;
    private  String complement;
    private  String city;
    private  String postalCode;
    private  Float charge;
    private  Float rent;
    private  Float deposit;

}
