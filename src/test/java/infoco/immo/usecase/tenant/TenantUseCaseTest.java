package infoco.immo.usecase.tenant;


import com.github.javafaker.Faker;
import infoco.immo.ObjectTesting.tenants.TenantsObjectTest;
import infoco.immo.configuration.BeanConfiguration;
import infoco.immo.core.Tenants;
import infoco.immo.database.SQL.tenant.TenantRepository;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ImportAutoConfiguration()
class TenantUseCaseTest {

    private final Tenants tenants = TenantsObjectTest.getTenant();

        @Autowired
        DataSource dataSource;

        @Autowired
        JdbcTemplate jdbcTemplate;

        @Autowired
        Faker faker;


    @Autowired
    BeanConfiguration beanConfiguration;

    @Autowired
    TenantRepository tenantRepository;



    @BeforeEach
    void beforeEach(){
        tenants.setId(UUID.randomUUID());
        tenants.setEmail(faker.internet().emailAddress());
        tenantRepository.create(tenants);
    }

    @AfterEach
    void afterEach(){
        jdbcTemplate.execute("DELETE FROM immo.tenant");
    }

    @Test
    void createTest() throws SQLException {
        tenants.setEmail(faker.internet().emailAddress());
        Tenants created = tenants;
        UUID tenantId = beanConfiguration.tenantUseCase().create(created);
        created.setId(tenantId);
        Tenants get = beanConfiguration.tenantUseCase().get(created);
        Assertions.assertEquals(created.getEmail(), get.getEmail());
    }

    @Test
    void getTest() {
        Tenants tenant = beanConfiguration.tenantUseCase().get(tenants);
        Assertions.assertEquals(tenant.getEmail(), tenants.getEmail());
    }


    @Test
    void getAllTest(){
        generated();
        List<Tenants> listTenant = beanConfiguration.tenantUseCase().get();
        Assert.assertEquals(10, listTenant.size());
    }

    @Test
    void deleteTest() {
        beanConfiguration.tenantUseCase().delete(tenants.getId());
        Tenants getTenant = beanConfiguration.tenantUseCase().get(tenants);
        Assert.assertNull(getTenant);
    }


    @Test
    void updateTest() {

        tenants.setName(faker.name().name());
        beanConfiguration.tenantUseCase().update(tenants);
        Tenants getTenant = beanConfiguration.tenantUseCase().get(tenants);
        Assertions.assertEquals(tenants.getName(), getTenant.getName());
    }

    private void generated(){
        int i = 1;
        while (i < 10) {
            beforeEach();
            i++;
        }
    }

}
