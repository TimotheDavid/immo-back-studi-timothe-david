package infoco.immo.database.SQL.tenant;

import infoco.immo.core.Tenants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public class TenantRepository implements TenantRepositoryI {

    @Autowired
    private JdbcTemplate db;

    @Override
    public void create(Tenants tenants) {
        final String SQL = "INSERT INTO immo.tenant( uuid, firstname, username, birthdate, birthplace, email, second_email, phone, civility) VALUES (?,?,?,?,?,?,?,?,CAST( ? AS immo.CIVILITY))";
        db.update(SQL, ps -> {
            int nthPlace = 1;
            ps.setObject(nthPlace++, tenants.getId());
            ps.setString(nthPlace++, tenants.getFirstName());
            ps.setString(nthPlace++, tenants.getName());
            ps.setString(nthPlace++, tenants.getBirthDate());
            ps.setString(nthPlace++, tenants.getBirthPlace());
            ps.setString(nthPlace++, tenants.getEmail());
            ps.setString(nthPlace++, tenants.getSecondEmail());
            ps.setString(nthPlace++, tenants.getPhone());
            ps.setString(nthPlace++, String.valueOf(tenants.getCivility()));
        });
    }

        @Override
    public Tenants get(Tenants tenants) {
        final String SQL = "SELECT * FROM immo.tenant WHERE uuid = ? AND deleted is false ";
        return  db.query(SQL, new TenantMapper(), tenants.getId()).stream().findFirst().orElse(null);
    }


    @Override
    public List<Tenants> get(){
        final String SQL = "SELECT * FROM immo.tenant WHERE deleted is false";
        return db.query(SQL, new TenantMapper());
    }

    @Override
    public void delete(UUID tenantId){
        final String SQL = "UPDATE immo.tenant SET deleted = true WHERE uuid = ?";
        db.update(SQL, tenantId);
    }

    @Override
    public List<TenantBalanceSheet> TenantBalanceSheet(UUID tenantId) {
        final String SQL = "select amount, sens, from_type, date_payment, origin, r.rent from immo.payment\n" +
                "join immo.rent r on r.uuid = payment.rentid\n" +
                "join immo.tenant t on r.tenantid = t.uuid\n" +
                "where t.uuid = ? \n" +
                "order by date_payment::timestamptz;";
        return db.query(SQL,new TenantBalanceSheetMapper(), tenantId);
    }

    @Override
    public void update(Tenants tenants) {
        final String SQL = "UPDATE immo.tenant SET  firstname = ?, username  = ?,  birthdate = ? , birthplace = ? , email = ? , second_email = ?, phone = ? , civility = CAST(? AS immo.civility)  WHERE uuid = ? ";
        db.update(SQL, ps -> {
            int nthPlace = 1;
            ps.setString(nthPlace++, tenants.getFirstName());
            ps.setString(nthPlace++, tenants.getName());
            ps.setString(nthPlace++, tenants.getBirthDate());
            ps.setString(nthPlace++, tenants.getBirthPlace());
            ps.setString(nthPlace++, tenants.getEmail());
            ps.setString(nthPlace++, tenants.getSecondEmail());
            ps.setString(nthPlace++, tenants.getPhone());
            ps.setString(nthPlace++, tenants.getCivility());
            ps.setObject(nthPlace++, tenants.getId());
        });
    }



}
