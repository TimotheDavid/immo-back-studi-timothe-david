package infoco.immo.http.rent.mapper;

import infoco.immo.core.Rent;
import infoco.immo.core.RentDataResponse;
import infoco.immo.core.RentTenant;
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

    public static Rent createDTOToRent(CreateRentDTO rent){
        if ( rent == null ) {
            return null;
        }

        Rent rentObject = new Rent();

        rentObject.setAmountRent( rent.getAmountRent() );
        rentObject.setInDate( rent.getInDate() );
        rentObject.setDescriptionIn( rent.getDescriptionIn() );
        rentObject.setOutDate( rent.getOutDate() );
        rentObject.setDescriptionOut( rent.getDescriptionOut() );
        rentObject.setDeposit( rent.getDeposit() );
        rentObject.setAgencyPourcent( rent.getAgencyPourcent() );
        rentObject.setApartmentId( rent.getApartmentId() );

        return rentObject;
    }

    public static CreateRentDTO domaineToCreateDTO(Rent rent) {
        if ( rent == null ) {
            return null;
        }

        CreateRentDTO createRentDTO = new CreateRentDTO();

        createRentDTO.setAmountRent( rent.getAmountRent() );
        createRentDTO.setInDate( rent.getInDate() );
        createRentDTO.setDescriptionIn( rent.getDescriptionIn() );
        createRentDTO.setOutDate( rent.getOutDate() );
        createRentDTO.setDescriptionOut( rent.getDescriptionOut() );
        createRentDTO.setDeposit( rent.getDeposit() );
        createRentDTO.setAgencyPourcent( rent.getAgencyPourcent() );
        createRentDTO.setApartmentId( rent.getApartmentId() );

        return createRentDTO;
    }

    public abstract Rent updateRentToRent(UpdateRentDTO rentDTO);

    public static CreateRentDTO rentToCreateDTO(Rent rent) {

        if (rent == null) {
            return null;
        }

        CreateRentDTO createRentDTO = new CreateRentDTO();

        createRentDTO.setAmountRent(rent.getAmountRent());
        createRentDTO.setInDate(rent.getInDate());
        createRentDTO.setDescriptionIn(rent.getDescriptionIn());
        createRentDTO.setOutDate(rent.getOutDate());
        createRentDTO.setDescriptionOut(rent.getDescriptionOut());
        createRentDTO.setDeposit(rent.getDeposit());
        createRentDTO.setAgencyPourcent(rent.getAgencyPourcent());



        return createRentDTO;

    }

    public abstract RentResponse domaineToResponse(Rent rent);

    public abstract RentResponse rentResponseDataToRentResponse(RentDataResponse rent);
}
