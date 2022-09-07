package infoco.immo.database.rent;

import infoco.immo.core.Rent;
import infoco.immo.usecase.rent.RentResponse;

import java.util.UUID;

public interface RentRepositoryI {
    public UUID create(Rent rent);
    public RentResponse get(Rent rent);
    public UUID update(Rent rent);
    public void delete(UUID rentId);
}
