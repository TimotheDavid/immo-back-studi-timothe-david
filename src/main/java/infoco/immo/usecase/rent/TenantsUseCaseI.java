package infoco.immo.usecase.rent;

import infoco.immo.core.Rent;

import java.util.UUID;

public interface TenantsUseCaseI {
    public UUID create(Rent rent);
    public RentResponse get(Rent rent);
    public UUID update(Rent rent);
    public void delete(UUID rentId);
}
