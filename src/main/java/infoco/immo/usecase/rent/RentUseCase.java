package infoco.immo.usecase.rent;

import infoco.immo.core.Rent;
import infoco.immo.database.SQL.rent.RentRepositoryI;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class RentUseCase {

    private RentRepositoryI rentRepositoryI;

    public RentUseCase(RentRepositoryI rentRepositoryI) {
        this.rentRepositoryI = rentRepositoryI;
    }

    public UUID create(Rent rent) {
        rent.setId(UUID.randomUUID());
        rentRepositoryI.create(rent);
        return rent.getId();
    }

    public List<Rent> get() { return rentRepositoryI.get();};
    public Rent get(Rent rent){
        return rentRepositoryI.get(rent);
    }
    public void update(Rent rent){
        rentRepositoryI.update(rent);

    }
    public void delete(UUID rentId){
        rentRepositoryI.delete(rentId);
    }
}
