package infoco.immo.http.appartement.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateApartmentDTO {
    private  String address;
    private  String complement;
    private  String city;
    private  String postalCode;
    private  Float charge;
    private  Float rent;
    private  Float deposit;
    private String  id;
}