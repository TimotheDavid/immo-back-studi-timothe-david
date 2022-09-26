package infoco.immo.usecase.rent;

import infoco.immo.core.*;
import infoco.immo.database.SQL.appartment.ApartmentRepositoryI;
import infoco.immo.database.SQL.payment.PaymentRepository;
import infoco.immo.database.SQL.payment.PaymentRepositoryI;
import infoco.immo.database.SQL.rent.RentRepositoryI;
import infoco.immo.database.SQL.tenant.TenantRepositoryI;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class RentUseCase {

    private RentRepositoryI rentRepositoryI;

    private TenantRepositoryI tenantRepositoryI;

    private ApartmentRepositoryI apartmentRepositoryI;

    private PaymentRepositoryI paymentRepositoryI;

    public RentUseCase(RentRepositoryI rentRepositoryI, ApartmentRepositoryI apartmentRepositoryI, TenantRepositoryI tenantRepositoryI, PaymentRepositoryI paymentRepositoryI) {
        this.rentRepositoryI = rentRepositoryI;
        this.apartmentRepositoryI = apartmentRepositoryI;
        this.tenantRepositoryI = tenantRepositoryI;
        this.paymentRepositoryI = paymentRepositoryI;
    }

    public UUID create(Rent rent) {
        rent.setId(UUID.randomUUID());
        rentRepositoryI.create(rent);
        createPayment(rent);
        return rent.getId();
    }

    private void createPayment(Rent rent){
        LocalDateTime localDateTime = LocalDateTime.now();
        Payment payment = Payment.builder()
                .id(UUID.randomUUID())
                .amount(rent.getAmountRent())
                .rentId(rent.getId())
                .datePayment(localDateTime.toString())
                .fromType(FromType.CAUTION)
                .typePayment(TypePayment.CHEQUE)
                .origin(Origin.LOCATAIRE)
                .sens(true)
                .build();
        paymentRepositoryI.create(payment);

    }
    public List<RentData> get() {
        return rentRepositoryI.get();
    }

    public Rent get(Rent rent) {
        return rentRepositoryI.get(rent);
    }

    public void update(Rent rent) {
        rentRepositoryI.update(rent);

    }

    public void delete(UUID rentId) {
        rentRepositoryI.delete(rentId);
    }

    public List<RentTenant> getAllRentTenant() {
        return rentRepositoryI.getAllRentTenant();
    }

    public List<RentDataResponse> getDataResponse() {
        return rentRepositoryI.getDataResponse();

    }
}
