package infoco.immo.usecase.rent;

import infoco.immo.core.Rent;
import infoco.immo.core.RentDataResponse;
import infoco.immo.core.RentTenant;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

public interface RentUseCaseI {
    void  create(Rent rent);
    Rent  get(Rent rent);


    List<RentDataResponse> getDataResponse();

    List<Rent> get();
    void update(Rent rent);
    void delete(UUID rentId);




    InputStream generateRentReceipt(String from, String to, String rentId) throws IOException;

    List<RentTenant> getAllRentTenant();
}
