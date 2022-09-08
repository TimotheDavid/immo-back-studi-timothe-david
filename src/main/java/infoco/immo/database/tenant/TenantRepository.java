package infoco.immo.database.tenant;

import infoco.immo.core.Tenants;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.SQLException;


@Repository
public class TenantRepository implements TenantRepositoryI {

    private JdbcTemplate db;

    public void setDataSource(DataSource dataSource) {
        db = new JdbcTemplate(dataSource);
    }

    @Override
    public void create(Tenants tenants) throws SQLException {
        final String SQL = "INSERT INTO immo.tenant(uuid, firstname, name, birthdate, birthplace, email, second_email, phone, civilityid) VALUES (?,?,?,?,?,?,?,?,?)";
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
            ps.setInt(nthPlace++, tenants.getCivility());
        });
    }

        @Override
    public Tenants get(Tenants tenants) {
        final String SQL = "SELECT * FROM immo.tenant WHERE uuid = ? ";
        return  db.query(SQL, new TenantMapper(), tenants.getId()).stream().findFirst().orElse(null);
    }

    @Override
    public void update(Tenants tenants) {
        final String SQL = "UPDATE immo.tenant SET firstname = ?, firstname = ?, birthdate = ? , birthplace = ? , email = ? , second_email = ?, phone = ? , civilityId = ?  WHERE uuid = ? ";
        db.update(SQL, ps -> {
            int nthPlace = 1;
            ps.setString(nthPlace++, tenants.getFirstName());
            ps.setString(nthPlace++, tenants.getName());
            ps.setString(nthPlace++, tenants.getBirthDate());
            ps.setString(nthPlace++, tenants.getBirthPlace());
            ps.setString(nthPlace++, tenants.getEmail());
            ps.setString(nthPlace++, tenants.getSecondEmail());
            ps.setString(nthPlace++, tenants.getPhone());
            ps.setInt(nthPlace++, tenants.getCivility());
            ps.setObject(nthPlace++, tenants.getId());
        });
    }

}
