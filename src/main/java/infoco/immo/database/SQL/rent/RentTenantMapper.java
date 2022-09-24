package infoco.immo.database.SQL.rent;

import infoco.immo.core.RentTenant;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class RentTenantMapper implements RowMapper<RentTenant> {
    @Override
    public RentTenant mapRow(ResultSet rs, int rowNum) throws SQLException {
        RentTenant rentTenant = new RentTenant();
        rentTenant.setRent(rs.getObject("uuid", UUID.class));
        rentTenant.setEmail(rs.getString("email"));
        return rentTenant;
    }
}
