package infoco.immo.configuration;

import infoco.immo.database.SQL.rent.RentRepository;
import infoco.immo.database.SQL.tenant.TenantRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.parameters.P;

import javax.sql.DataSource;

public class repositoryTest {


    private JdbcTemplate db;

    private DataSource dataSource(){
        PostgresDataConfigurationTest postgresDataConfigurationTest = new PostgresDataConfigurationTest();
        return postgresDataConfigurationTest.dataSource();
    }

}
