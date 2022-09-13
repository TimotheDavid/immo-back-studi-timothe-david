package infoco.immo.http.appartement.apartmentMapper;


import infoco.immo.core.Apartment;
import infoco.immo.http.appartement.dto.CreateApartmentDTO;
import infoco.immo.http.appartement.dto.UpdateApartmentDTO;
import infoco.immo.http.appartement.response.ApartmentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ApartmentMapper {

    ApartmentMapper INSTANCE = Mappers.getMapper(ApartmentMapper.class);

    CreateApartmentDTO apartmentTOcreateDTO(Apartment apartment);

    UpdateApartmentDTO updateToApartment(Apartment apartment);

    Apartment CreateDTOtoDomain(CreateApartmentDTO createApartmentDTO);

    Apartment updateDTOToDomain(UpdateApartmentDTO updateApartmentDTO);

    ApartmentResponse domainToResponse(Apartment apartment);
}
