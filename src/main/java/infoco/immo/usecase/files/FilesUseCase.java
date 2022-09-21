package infoco.immo.usecase.files;

import infoco.immo.core.Apartment;
import infoco.immo.core.Rent;
import infoco.immo.core.RentReceiptData;
import infoco.immo.core.Tenants;
import infoco.immo.database.SQL.appartment.ApartmentRepositoryI;
import infoco.immo.database.SQL.payment.PaymentRepositoryI;
import infoco.immo.database.SQL.rent.RentRepositoryI;
import infoco.immo.database.SQL.tenant.TenantRepositoryI;
import infoco.immo.files.FilesGeneratorI;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public
class FilesUseCase {

    public final PaymentRepositoryI paymentRepositoryI;

    public final FilesGeneratorI filesGeneratorI;

    public final ApartmentRepositoryI apartmentRepository;

    public final RentRepositoryI rentRepositoryI;

    private final TenantRepositoryI tenantRepository;

    public InputStream generateRentReceipt(String rentId, String from, String to) throws IOException {

        List<RentReceiptData> rentReceiptData = paymentRepositoryI.generateRentReceipt(from, to, rentId);
        Rent rentObject = Rent.builder().id(UUID.fromString(rentId)).build();
        Rent rent = rentRepositoryI.get(rentObject);
        Apartment apartmentObject = Apartment.builder().id(rent.getApartmentId()).build();
        Tenants tenantsObject = Tenants.builder().id(rent.getTenantsId()).build();
        Apartment apartment = apartmentRepository.get(apartmentObject);
        Tenants tenants = tenantRepository.get(tenantsObject);


        if(verifyInRules(rentReceiptData)){
           return  filesGeneratorI.generateRentReceipt(rentReceiptData, apartment, tenants, from, to);
        }

        return null;
    }

    private boolean verifyInRules(List<RentReceiptData> rentReceiptDataList) {

            float finalAmount = 0;
            for(RentReceiptData receipt: rentReceiptDataList ){

                if(receipt.getSens()){
                    finalAmount += receipt.getAmount();
                } else {
                    finalAmount -= receipt.getAmount();
                }
            }

            return finalAmount > 0;


    }


}
