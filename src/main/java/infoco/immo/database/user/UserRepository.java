package infoco.immo.database.user;

import infoco.immo.core.User;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.UUID;

public class UserRepository implements UserRepositoryI {

    private JdbcTemplate db;

    public void setDataSource(DataSource dataSource) {
        db = new JdbcTemplate(dataSource);
    }

    @Override
    public void create(User user) {

    }

    @Override
    public User get(UUID userId) {
        return null;
    }

    @Override
    public User getByToken(String token) {
        return null;
    }
}
