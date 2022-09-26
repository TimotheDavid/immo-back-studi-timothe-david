package infoco.immo.database.SQL.rent;

import infoco.immo.core.Rent;
import infoco.immo.core.RentData;
import infoco.immo.core.RentDataResponse;
import infoco.immo.core.RentTenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class RentRepository implements RentRepositoryI {


    @Autowired
    private JdbcTemplate db;

    @Override
    public void create(Rent rent) {
        final String SQL = " INSERT INTO immo.rent(uuid, rent, in_date, in_description, out_date, out_description, deposit, agency_pourcent,apartmentId,tenantId, out_description_tenant, in_description_tenant) VALUES(?,?,?,?,?,?,?,?,?,?,?,?) ";
        db.update(SQL, ps -> {
            int nthPlace = 1;
            ps.setObject(nthPlace++, rent.getId());
            ps.setFloat(nthPlace++, rent.getAmountRent());
            ps.setString(nthPlace++, rent.getInDate());
            ps.setString(nthPlace++, rent.getDescriptionIn());
            ps.setString(nthPlace++, rent.getOutDate());
            ps.setString(nthPlace++, rent.getDescriptionOut());
            ps.setFloat(nthPlace++, rent.getDeposit());
            ps.setFloat(nthPlace++, rent.getAgencyPourcent());
            ps.setObject(nthPlace++, rent.getApartmentId());
            ps.setObject(nthPlace++, rent.getTenantsId());
            ps.setString(nthPlace++, rent.getDescriptionOutTenant());
            ps.setString(nthPlace++, rent.getDescriptionInTenant());
        });
    }

    @Override
    public List<RentDataResponse> getDataResponse() {
        final String SQL = "select r.uuid, r.rent, in_date, in_description, out_date, out_description, r.deposit, address, email from immo.rent r\n" +
                "join immo.apartment on r.apartmentid = apartment.uuid\n" +
                "join immo.tenant on r.tenantid = tenant.uuid";
        return db.query(SQL, new RentDataMapper());

    }
    @Override
    public Rent get(Rent rent) {
        final String SQL = "SELECT * FROM immo.rent WHERE uuid = ?";
        return db.query(SQL, new RentMapper(), rent.getId()).stream().findFirst().orElse(null);
    }

    @Override
    public void update(Rent rent) {
        final String SQL = "UPDATE immo.rent SET rent = ?, in_date = ?::timestamptz, in_description = ?, out_date = ?::timestamptz , out_description = ?, deposit = ?, agency_pourcent = ?, in_description_tenant = ?, out_description_tenant = ?  WHERE uuid = ? ";
        db.update(SQL, ps -> {
            int nthPlace = 1;
            ps.setFloat(nthPlace++, rent.getAmountRent());
            ps.setString(nthPlace++, rent.getInDate());
            ps.setString(nthPlace++, rent.getDescriptionIn());
            ps.setString(nthPlace++, rent.getOutDate());
            ps.setString(nthPlace++, rent.getDescriptionOut());
            ps.setFloat(nthPlace++, rent.getDeposit());
            ps.setFloat(nthPlace++, rent.getAgencyPourcent() == null ? 0: rent.getAgencyPourcent()  );
            ps.setString(nthPlace++, rent.getDescriptionInTenant());
            ps.setString(nthPlace++, rent.getDescriptionOutTenant());
            ps.setObject(nthPlace++, rent.getId());
        });
    }

    @Override
    public void delete(UUID rentId) {
        final String SQL = "DELETE FROM immo.rent WHERE uuid = ? ";
        db.update(SQL, rentId);

    }

    @Override
    public List<RentData> get() {
        final String SQL = "SELECT r.*, t.email, a.address, in_description_tenant, out_description_tenant FROM immo.rent r\n" +
                "join immo.apartment a on r.apartmentid = r.apartmentid\n" +
                "join immo.tenant t  on  r.tenantid = t.uuid;";
        return db.query(SQL, new RentMapperData());
    }

    @Override
    public List<RentTenant> getAllRentTenant(){
        final String SQL = "select rent.uuid, email from immo.rent\n" +
                "join immo.tenant on rent.tenantid = tenant.uuid\n" +
                "where deleted IS FALSE ";
        return db.query(SQL, new RentTenantMapper());
    }
}
