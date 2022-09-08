package infoco.immo.usecase.appartment;

import infoco.immo.core.Apartment;

import java.util.UUID;

public interface ApartmentUseCaseI {
    public UUID create(Apartment apartment);
    public Apartment get(Apartment apartment);
    public Apartment update(Apartment apartment);
    public void delete(UUID apartmentId);
    public ApartmentPaymentData getPaymentByApartment(UUID apartment);
}
