package infoco.immo.files;

import infoco.immo.core.Apartment;
import infoco.immo.core.RentReceiptData;
import infoco.immo.core.Tenants;
import infoco.immo.database.SQL.tenant.TenantBalanceSheet;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface FilesGeneratorI {

    InputStream generateRentReceipt(List<RentReceiptData> rentReceiptDataList, Apartment apartment, Tenants tenants, String from, String to) throws IOException;


    InputStream generateBalanceSheet(List<TenantBalanceSheet> tenantBalanceSheets, Tenants tenants, Apartment apartment) throws IOException;
}
