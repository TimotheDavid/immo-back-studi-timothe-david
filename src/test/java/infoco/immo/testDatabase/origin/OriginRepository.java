package infoco.immo.testDatabase.origin;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

@RequiredArgsConstructor
public class OriginRepository {

    private JdbcTemplate db;

    public void setDataSource(DataSource dataSource) {
        db = new JdbcTemplate(dataSource);
    }

    public List<Origin> get(){
        final String SQL = "SELECT * FROM immo.origin";
        return db.query(SQL, new OriginMapper());

    }


}
