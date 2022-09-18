package infoco.immo.database.SQL.tenant;

import infoco.immo.core.Tenants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.UUID;


@Repository
public class TenantRepository implements TenantRepositoryI {

    @Autowired
    private JdbcTemplate db;

    public void setDataSource(DataSource dataSource) {
        db = new JdbcTemplate(dataSource);
    }


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
        final String SQL = "SELECT * FROM immo.tenant WHERE uuid = ? ";
        return  db.query(SQL, new TenantMapper(), tenants.getId()).stream().findFirst().orElse(null);
    }

    public List<Tenants> get(){
        final String SQL = "SELECT * FROM immo.tenant";
        return db.query(SQL, new TenantMapper());
    }

    public void delete(UUID tenantId){
        final String SQL = "DELETE FROM immo.tenant WHERE uuid = ?";
        db.update(SQL, tenantId);
    }

    @Override
    public void patchWithRent(UUID tenantId, UUID rentId) {
        final String SQL = "UPDATE immo.tenant SET rentid = ? WHERE uuid = ?";
        db.update(SQL, tenantId, rentId);
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
