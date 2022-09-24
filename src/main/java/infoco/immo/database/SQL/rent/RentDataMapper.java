package infoco.immo.database.SQL.rent;

import infoco.immo.core.RentDataResponse;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class RentDataMapper implements RowMapper<RentDataResponse> {
    @Override
    public RentDataResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
        RentDataResponse rentDataResponse = new RentDataResponse();
        rentDataResponse.setId(rs.getObject("uuid",  UUID.class));
        rentDataResponse.setInDate(rs.getString("in_date"));
        rentDataResponse.setDescriptionIn(rs.getString("in_description"));
        rentDataResponse.setOutDate(rs.getString("out_date"));
        rentDataResponse.setDescriptionOut(rs.getString("out_description"));
        rentDataResponse.setDeposit(rs.getFloat("deposit"));
        rentDataResponse.setAddress(rs.getString("address"));
        rentDataResponse.setEmail(rs.getString("email"));
        return rentDataResponse;
    }
}
