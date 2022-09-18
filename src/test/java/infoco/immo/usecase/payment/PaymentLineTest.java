package infoco.immo.usecase.payment;

import infoco.immo.core.Apartment;
import infoco.immo.core.Rent;
import infoco.immo.core.Tenants;
import infoco.immo.database.SQL.appartment.ApartmentRepository;
import infoco.immo.database.SQL.rent.RentRepository;
import infoco.immo.database.SQL.tenant.TenantRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.UUID;
@AllArgsConstructor
@RequiredArgsConstructor
public class PaymentLineTest {

    private TenantRepository tenantRepository;
    private ApartmentRepository apartmentRepository;
    private RentRepository rentRepository;
    private Rent rent;
    private Apartment apartment;
    private Tenants tenant;

    private UUID createLine() {
        tenant.setId(UUID.randomUUID());
        tenantRepository.create(tenant);
        apartment.setId(UUID.randomUUID());
        apartmentRepository.create(apartment);
        rent.setTenantsId(tenant.getId());
        rent.setApartmentId(tenant.getId());
        rent.setId(UUID.randomUUID());
        rentRepository.create(rent);
        return rent.getId();
    }

    public  UUID createLinePayment() {
        return new PaymentLineTest().createLine();
    }


}
