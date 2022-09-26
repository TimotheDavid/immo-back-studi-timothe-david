package infoco.immo.database.SQL.rent;

import infoco.immo.core.Rent;
import infoco.immo.core.RentData;
import infoco.immo.core.RentDataResponse;
import infoco.immo.core.RentTenant;

import java.util.List;
import java.util.UUID;

public interface RentRepositoryI {
    void   create(Rent rent);

    List<RentDataResponse> getDataResponse();

    Rent get(Rent rent);
    void  update(Rent rent);
    void delete(UUID rentId);

    List<RentData> get();

    List<RentTenant> getAllRentTenant();
}
