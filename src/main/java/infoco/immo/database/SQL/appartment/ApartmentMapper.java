package infoco.immo.database.SQL.appartment;

import infoco.immo.core.Apartment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ApartmentMapper implements RowMapper<Apartment> {
    @Override
    public Apartment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Apartment apartment = new Apartment();
        apartment.setId(rs.getObject("uuid", UUID.class));
        apartment.setAddress(rs.getString("address"));
        apartment.setComplement(rs.getString("complement"));
        apartment.setCity(rs.getString("city"));
        apartment.setPostalCode(rs.getString("postal_code"));
        apartment.setCharge(rs.getFloat("charge"));
        apartment.setRent(rs.getFloat("rent"));
        apartment.setDeposit(rs.getFloat("deposit"));
        apartment.setDeleted(rs.getBoolean("deleted"));
        return apartment;
    }
}
