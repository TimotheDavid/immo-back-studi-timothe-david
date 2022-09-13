package infoco.immo.usecase.rent;

import infoco.immo.core.Rent;

import java.util.List;
import java.util.UUID;

public interface RentUseCaseI {
    public void  create(Rent rent);
    public Rent  get(Rent rent);

    public List<Rent> get();
    public void update(Rent rent);
    public void delete(UUID rentId);

}
