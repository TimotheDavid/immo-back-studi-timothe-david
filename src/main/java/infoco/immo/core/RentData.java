package infoco.immo.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
public class RentData {
    private UUID id;
    private  Float amountRent;
    private  String inDate;
    private  String descriptionIn;
    private  String outDate;
    private  String descriptionOut;
    private  Float deposit;
    private  Float agencyPourcent;
    private String address;
    private String email;
    private Float rent;
    private String descriptionInTenant;
    private String descriptionOutTenant;
}
