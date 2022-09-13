package infoco.immo.http.rent.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateRentDTO {
    private  Float rent;
    private  String inDate;
    private  String descriptionIn;

    private  String outDate;
    private  String descriptionOut;
    private  Float deposit;
    private  Float agencyPourcent;
    private UUID tenantsId;
    private  UUID apartmentId;
}
