package infoco.immo.database.SQL.user;

import infoco.immo.core.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getObject("uuid", UUID.class));
        user.setEmail(rs.getString("email"));
        user.setName(rs.getString("password"));
        return user;
    }
}
