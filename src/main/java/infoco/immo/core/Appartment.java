package infoco.immo.core;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class Appartment {
    private final String address;
    private final String complement;
    private final String city;
    private final String postalCode;
    private final Float charge;
    private final Float rent;
    private final Float deposit;
}
