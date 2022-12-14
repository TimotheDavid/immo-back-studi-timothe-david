package infoco.immo.database.SQL.rent;

import infoco.immo.core.Rent;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class RentMapper implements RowMapper<Rent> {
    @Override
    public Rent mapRow(ResultSet rs, int rowNum) throws SQLException {
        Rent rent = new Rent();
        rent.setId(rs.getObject("uuid", UUID.class));
        rent.setAmountRent(rs.getFloat("rent"));
        rent.setDeposit(rs.getFloat("deposit"));
        rent.setAgencyPourcent(rs.getFloat("agency_pourcent"));
        rent.setDescriptionOut(rs.getString("out_description"));
        rent.setOutDate(rs.getString("in_date"));
        rent.setDescriptionIn(rs.getString("in_description"));
        rent.setInDate(rs.getString("in_date"));
        rent.setTenantsId(rs.getObject("tenantid", UUID.class));
        rent.setApartmentId(rs.getObject("apartmentid", UUID.class));

        return rent;
    }
}
