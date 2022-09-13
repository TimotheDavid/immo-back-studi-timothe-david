package infoco.immo.database.SQL.authentication;

import infoco.immo.core.Authentication;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class AuthenticationMapper implements RowMapper<Authentication> {
    @Override
    public Authentication mapRow(ResultSet rs, int rowNum) throws SQLException {
        Authentication authentication = new Authentication();
        authentication.setExpires(rs.getString("expires"));
        authentication.setHash(rs.getString("hash"));
        authentication.setToken(rs.getString("token"));
        authentication.setUserId(rs.getObject("uuid", UUID.class));
        return authentication;
    }
}
