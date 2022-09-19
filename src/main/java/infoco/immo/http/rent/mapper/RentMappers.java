package infoco.immo.http.rent.mapper;

import infoco.immo.core.Rent;
import infoco.immo.http.rent.dto.CreateRentDTO;
import infoco.immo.http.rent.dto.UpdateRentDTO;
import infoco.immo.http.rent.response.RentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper(
        imports = {UUID.class}

)
public abstract class RentMappers {

    public static final RentMappers INSTANCE = Mappers.getMapper(RentMappers.class);

    public abstract Rent createDTOToRent(CreateRentDTO rent);

    public abstract Rent updateRentToRent(UpdateRentDTO rentDTO);

    public static CreateRentDTO rentToCreateDTO(Rent rent) {

        if (rent == null) {
            return null;
        }

        CreateRentDTO createRentDTO = new CreateRentDTO();

        createRentDTO.setAmountRent(rent.getAmount());
        createRentDTO.setInDate(rent.getInDate());
        createRentDTO.setDescriptionIn(rent.getDescriptionIn());
        createRentDTO.setOutDate(rent.getOutDate());
        createRentDTO.setDescriptionOut(rent.getDescriptionOut());
        createRentDTO.setDeposit(rent.getDeposit());
        createRentDTO.setAgencyPourcent(rent.getAgencyPourcent());



        return createRentDTO;

    }

    public abstract RentResponse domaineToResponse(Rent rent);
}
