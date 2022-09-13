package infoco.immo.ObjectTesting.rent;

import infoco.immo.core.Rent;
import infoco.immo.http.rent.dto.CreateRentDTO;

public class RentDomainToCreateDTO {

    private CreateRentDTO map(Rent rent){

        CreateRentDTO createRentDTO = new CreateRentDTO();
/*        createRentDTO.setAmount(rent.getAmount());
        createRentDTO.setAgencyPourcent(rent.getAgencyPourcent());
        createRentDTO.setDeposit(rent.getDeposit());
        createRentDTO.setDescriptionIn(rent.getDescriptionIn());
        createRentDTO.setInDate(rent.getInDate());
        createRentDTO.setOutDate(rent.getOutDate());
        createRentDTO.setDescriptionOut(rent.getDescriptionOut());*/
        return createRentDTO;
    }

    public static CreateRentDTO createMap(Rent rent){
        return new RentDomainToCreateDTO().map(rent);
    }

}
