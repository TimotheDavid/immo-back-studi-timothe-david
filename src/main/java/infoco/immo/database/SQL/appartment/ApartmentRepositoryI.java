package infoco.immo.database.SQL.appartment;

import infoco.immo.core.Apartment;

import java.util.List;
import java.util.UUID;

public interface ApartmentRepositoryI {
    public void create(Apartment apartment);
    public Apartment get(Apartment apartment);
    public List<Apartment> get();

    public void  update(Apartment apartment);
    public void delete(UUID apartmentId);

    public void patchWithRent(UUID apartmentId, UUID rentId);
    public PaymentDataByAppartment getPaymentByApartment(UUID apartmentId);

}
