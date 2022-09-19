package infoco.immo.database.SQL.tenant;

import infoco.immo.core.Tenants;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class TenantMapper implements RowMapper<Tenants> {
    @Override
    public Tenants mapRow(ResultSet rs, int rowNum) throws SQLException {
        Tenants tenant = new Tenants();
        tenant.setId(rs.getObject("uuid", UUID.class));
        tenant.setBirthDate(rs.getString("birthdate"));
        tenant.setFirstName(rs.getString("firstname"));
        tenant.setName(rs.getString("username"));
        tenant.setBirthPlace(rs.getString("birthplace"));
        tenant.setEmail(rs.getString("email"));
        tenant.setSecondEmail(rs.getString("second_email"));
        tenant.setPhone(rs.getString("phone"));
        tenant.setCivility(rs.getString("civility"));
        tenant.setRent(rs.getObject("rentid", UUID.class));
        return tenant;
    }
}
