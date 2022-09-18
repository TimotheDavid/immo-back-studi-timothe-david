package infoco.immo.database.SQL.authentication;

import infoco.immo.core.Authentication;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@RequiredArgsConstructor
@Repository
public class AuthenticationRepository implements AuthenticationRepositoryI{

    @Autowired
    private JdbcTemplate db;

    public void setDataSource(DataSource dataSource) {
        db = new JdbcTemplate(dataSource);
    }


    @Override
    public void create(Authentication authentication) {
    final String SQL = "INSERT  INTO immo.authentication(uuid, token, hash, expires, userid) VALUES (?,?,?,?,?)";
    db.update(SQL, ps -> {
        int nthPlace = 1;
        ps.setObject(nthPlace++, authentication.getUuid());
        ps.setString(nthPlace++, authentication.getToken());
        ps.setString(nthPlace++, authentication.getHash());
        ps.setString(nthPlace++, authentication.getExpires());
        ps.setObject(nthPlace++, authentication.getUserId());

    });
    }

    @Override
    public Authentication get(Authentication authentication) {
        final String SQL = "SELECT * FROM immo.authentication WHERE userid = ?";
        return db.query(SQL, new AuthenticationMapper(), authentication.getUserId()).stream().findFirst().orElse(null);
    }


    @Override
    public Authentication getByToken(String token) {
        final String SQL = "SELECT token, hash, expires,uuid FROM immo.authentication WHERE token = ?";
        return db.query(SQL, new AuthenticationMapper(),token).stream().findFirst().orElse(null);
    }
}
