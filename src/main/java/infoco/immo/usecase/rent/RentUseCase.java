package infoco.immo.usecase.rent;

import infoco.immo.core.Rent;
import infoco.immo.core.RentDataResponse;
import infoco.immo.core.RentTenant;
import infoco.immo.database.SQL.appartment.ApartmentRepository;
import infoco.immo.database.SQL.appartment.ApartmentRepositoryI;
import infoco.immo.database.SQL.rent.RentRepositoryI;
import infoco.immo.database.SQL.tenant.TenantRepositoryI;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

public class RentUseCase {

    private RentRepositoryI rentRepositoryI;

    private TenantRepositoryI tenantRepositoryI;

    private ApartmentRepositoryI apartmentRepository;

    public RentUseCase(RentRepositoryI rentRepositoryI, ApartmentRepositoryI apartmentRepositoryI, TenantRepositoryI tenantRepositoryI) {
        this.rentRepositoryI = rentRepositoryI;
        this.apartmentRepository = apartmentRepositoryI;
        this.tenantRepositoryI = tenantRepositoryI;
    }

    public UUID create(Rent rent) {
        rent.setId(UUID.randomUUID());
        rentRepositoryI.create(rent);
        return rent.getId();
    }

    public List<Rent> get() { return rentRepositoryI.get();}
    public Rent get(Rent rent){
        return rentRepositoryI.get(rent);
    }
    public void update(Rent rent){
        rentRepositoryI.update(rent);

    }
    public void delete(UUID rentId){
        rentRepositoryI.delete(rentId);
    }

    public List<RentTenant> getAllRentTenant(){
        return rentRepositoryI.getAllRentTenant();
    }

    public List<RentDataResponse> getDataResponse(){
        return rentRepositoryI.getDataResponse();

    }
}
