package infoco.immo.usecase.rent;

import infoco.immo.core.Rent;
import infoco.immo.database.SQL.appartment.ApartmentRepository;
import infoco.immo.database.SQL.appartment.ApartmentRepositoryI;
import infoco.immo.database.SQL.rent.RentRepositoryI;
import infoco.immo.database.SQL.tenant.TenantRepositoryI;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
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
        apartmentRepository.patchWithRent(rent.getApartmentId(), rent.getId());
        tenantRepositoryI.patchWithRent(rent.getTenantsId(), rent.getId());
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
}
