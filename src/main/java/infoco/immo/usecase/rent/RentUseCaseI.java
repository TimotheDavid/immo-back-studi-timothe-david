package infoco.immo.usecase.rent;

import infoco.immo.core.Rent;

import java.util.UUID;

public interface RentUseCaseI {
    public UUID create(Rent rent);
    public Rent  get(Rent rent);
    public void update(Rent rent);
    public void delete(UUID rentId);
}
