package infoco.immo.database.SQL.user;

import infoco.immo.core.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.UUID;

@Repository
public class UserRepository implements UserRepositoryI {

    @Autowired
    private JdbcTemplate db;

    @Override
    public void create(User user) {
        final String  SQL = "INSERT INTO immo.users(uuid, name, email, password) VALUES (?,?,?,?)";
        db.update(SQL, ps -> {
            int nthplace = 1;
            ps.setObject(nthplace++, user.getId());
            ps.setString(nthplace++, user.getName());
            ps.setString(nthplace++, user.getEmail());
            ps.setString(nthplace++, user.getPassword());
        });

    }

    @Override
    public User get(UUID userId) {
        final String SQL = "SELECT * FROM immo.users WHERE uuid = ?";
        return db.query(SQL, new UserMapper(), userId).stream().findFirst().orElse(null);
    }

    @Override
    public User getByToken(String token) {
        final String SQL = "SELECT * FROM immo.users JOIN immo.authentication a on users.uuid = a.userid WHERE a.token = ? ";
        return db.query(SQL, new UserMapper(), token).stream().findFirst().orElse(null);


    }

    @Override
    public User  login(User user) {
        final String SQL = "SELECT password, email,uuid FROM immo.users WHERE email = ?";
        return db.query(SQL, new UserMapper(), user.getEmail()).stream().findFirst().orElse(null);
    }

}
