package infoco.immo.database.SQL.rent;

import infoco.immo.core.Rent;
import infoco.immo.core.RentData;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class RentMapperData implements RowMapper<RentData> {
    @Override
    public RentData mapRow(ResultSet rs, int rowNum) throws SQLException {
        RentData rent = new RentData();
        rent.setId(rs.getObject("uuid", UUID.class));
        rent.setRent(rs.getFloat("rent"));
        rent.setDeposit(rs.getFloat("deposit"));
        rent.setAgencyPourcent(rs.getFloat("agency_pourcent"));
        rent.setDescriptionOut(rs.getString("out_description"));
        rent.setOutDate(rs.getString("in_date"));
        rent.setDescriptionIn(rs.getString("in_description"));
        rent.setInDate(rs.getString("in_date"));
        rent.setEmail(rs.getString("email"));
        rent.setAddress(rs.getString("address"));
        rent.setDescriptionInTenant(rs.getString("in_description_tenant"));
        rent.setDescriptionOutTenant(rs.getString("out_description_tenant"));
        return rent;
    }
}
