package infoco.immo.http.appartement.response;

import infoco.immo.core.Apartment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApartmentResponse {

    private String id;
    private String address;
    private String complement;
    private String city;
    private String postal_code;
    private Float charge;
    private Float rent;
    private Float deposit;
}
