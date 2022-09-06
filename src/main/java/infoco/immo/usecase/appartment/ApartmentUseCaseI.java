package infoco.immo.usecase.appartment;

import infoco.immo.core.Appartment;

import java.util.UUID;

public interface ApartmentUseCaseI {
    public UUID create(Appartment appartment);
    public Appartment get(Appartment appartment);
    public Appartment update(Appartment appartment);
    public void delete(UUID apartmentId);
    public ApartmentPaymentData getPaymentByApartment(UUID apartment);
}
