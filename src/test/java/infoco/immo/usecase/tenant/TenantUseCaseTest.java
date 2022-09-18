package infoco.immo.usecase.tenant;


import infoco.immo.ObjectTesting.tenants.TenantsObjectTest;
import infoco.immo.configuration.BeanConfiguration;
import infoco.immo.configuration.PostgresDataConfigurationTest;
import infoco.immo.core.Tenants;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.jdbc.AutoConfigureDataJdbc;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ImportAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
class TenantUseCaseTest {

    private final Tenants tenants = TenantsObjectTest.getTenant();

    @Autowired
    BeanConfiguration beanConfiguration;



    @Test
    public void createTest() throws SQLException {
        Tenants created = tenants;

        UUID tenantId = beanConfiguration.tenantUseCase().create(created);
        created.setId(tenantId);
        Tenants get = beanConfiguration.tenantUseCase().get(created);
        Assertions.assertEquals(created.getEmail(), get.getEmail());
    }

    @Test
    public void getTest() throws SQLException {
        Tenants createdTenant = tenants;
        beanConfiguration.tenantUseCase().create(createdTenant);
        Tenants tenant = beanConfiguration.tenantUseCase().get(createdTenant);
        Assertions.assertEquals(tenant.getEmail(), createdTenant.getEmail());
    }


    @Test
    public void getAllTest(){
        List<Tenants> listTenant = beanConfiguration.tenantUseCase().get();
        Assert.assertTrue(listTenant.size() > 0);
    }

    @Test
    public void deleteTest() throws SQLException {
        UUID tenantId = beanConfiguration.tenantUseCase().create(tenants);
        beanConfiguration.tenantUseCase().delete(tenantId);
        Tenants getTenant = beanConfiguration.tenantUseCase().get(tenants);
        Assert.assertNull(getTenant);
    }


    @Test
    public void updateTest() throws SQLException {

        UUID tenantId = beanConfiguration.tenantUseCase().create(tenants);
        Tenants createTenant = tenants;
        createTenant.setId(tenantId);
        beanConfiguration.tenantUseCase().update(createTenant);
        Tenants getTenant = beanConfiguration.tenantUseCase().get(createTenant);
        Assertions.assertEquals(createTenant.getName(), getTenant.getName());
        Assertions.assertEquals(tenantId, getTenant.getId());
    }

}
