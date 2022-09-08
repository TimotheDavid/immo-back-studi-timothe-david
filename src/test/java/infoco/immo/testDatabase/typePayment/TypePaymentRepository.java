package infoco.immo.testDatabase.typePayment;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class TypePaymentRepository {

    private JdbcTemplate db;

    public void setDataSource(DataSource dataSource) {
        db = new JdbcTemplate(dataSource);
    }



}
