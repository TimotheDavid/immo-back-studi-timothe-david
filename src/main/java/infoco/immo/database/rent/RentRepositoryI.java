package infoco.immo.database.rent;

import infoco.immo.core.Rent;
import infoco.immo.usecase.rent.RentResponse;

import java.util.UUID;

public interface RentRepositoryI {
    public void   create(Rent rent);
    public Rent  get(Rent rent);
    public void  update(Rent rent);
    public void delete(UUID rentId);
}
