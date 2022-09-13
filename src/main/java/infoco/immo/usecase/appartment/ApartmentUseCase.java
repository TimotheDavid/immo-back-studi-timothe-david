package infoco.immo.usecase.appartment;

import infoco.immo.core.Apartment;
import infoco.immo.database.SQL.appartment.ApartmentRepositoryI;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

public class ApartmentUseCase  {

    private final ApartmentRepositoryI apartmentRepositoryI;

    public ApartmentUseCase(ApartmentRepositoryI apartmentRepositoryI){
        this.apartmentRepositoryI = apartmentRepositoryI;
    }

    public UUID create(Apartment apartment){
        apartment.setId(UUID.randomUUID());
        apartmentRepositoryI.create(apartment);
        return apartment.getId();
    }
    public Apartment get(Apartment apartment){
        return apartmentRepositoryI.get(apartment);
    }

    public List<Apartment> get(){ return apartmentRepositoryI.get(); };
    public void  update(Apartment apartment){
        apartmentRepositoryI.update(apartment);
    }
    public void delete(UUID apartmentId){
        apartmentRepositoryI.delete(apartmentId);
    }
}
