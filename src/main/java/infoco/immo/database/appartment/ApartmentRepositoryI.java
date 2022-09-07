package infoco.immo.database.appartment;

import infoco.immo.core.Appartment;

import java.util.UUID;

public interface ApartmentRepositoryI {
    public UUID create(Appartment appartment);
    public Appartment get(UUID apartmentId);
    public UUID update(Appartment appartment);
    public void delete(UUID apartmentId);
    public PaymentDataByAppartment getPaymentByApartment(UUID apartmentId);

}
