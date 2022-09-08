package infoco.immo.usecase.appartment;

import infoco.immo.core.Apartment;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class ApartmentUseCase  {

    public final  ApartmentRepositoryI apartmentRepositoryI;
    public UUID create(Apartment apartment){
        apartment.setId(UUID.randomUUID());
        apartmentRepositoryI.create(apartment);
        return apartment.getId();
    }
    public Apartment get(Apartment apartment){
        return apartmentRepositoryI.get(apartment);
    }
    public void  update(Apartment apartment){
        apartmentRepositoryI.update(apartment);
    }
    public void delete(UUID apartmentId){
        apartmentRepositoryI.delete(apartmentId);
    }
}
