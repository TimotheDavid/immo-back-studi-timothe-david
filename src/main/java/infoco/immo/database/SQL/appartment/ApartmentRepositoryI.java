package infoco.immo.database.SQL.appartment;

import infoco.immo.core.Apartment;

import java.util.List;
import java.util.UUID;

public interface ApartmentRepositoryI {
    void create(Apartment apartment);
    Apartment get(Apartment apartment);
    List<Apartment> get();

    void  update(Apartment apartment);
    void delete(UUID apartmentId);


}
