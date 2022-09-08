package infoco.immo.testDatabase.origin;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class OriginMapper implements RowMapper<Origin> {
    @Override
    public Origin mapRow(ResultSet rs, int rowNum) throws SQLException {
        Origin origin = new Origin();
        origin.setOrigin(rs.getString("origin"));
        origin.setUuid(rs.getObject("uuid", UUID.class));
        return origin;
    }
}
