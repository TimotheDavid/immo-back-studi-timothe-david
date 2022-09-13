package infoco.immo.http.appartement.dto;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateApartmentDTO {
    private  String address;
    private  String complement;
    private  String city;
    private  String postalCode;

    private  Float charge;
    private  Float rent;
    private  Float deposit;
}
