package infoco.immo.usecase.tenant;


import infoco.immo.ObjectTesting.tenants.TenantsObjectTest;
import infoco.immo.configuration.PostgresDataConfigurationTest;
import infoco.immo.core.Tenants;
import infoco.immo.database.SQL.tenant.TenantRepository;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;


@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
class TenantUseCaseTest {

    private final Tenants tenants = TenantsObjectTest.getTenant();

    private final TenantUseCase tenantUseCase = beforeAllTenant();

    static TenantUseCase beforeAllTenant() {
        TenantRepository tenantRepository = new TenantRepository();
        tenantRepository.setDataSource(new PostgresDataConfigurationTest().dataSource());
        return new TenantUseCase(tenantRepository);
    }


    @BeforeAll
    static void beforeAll() {
        beforeAllTenant();
    }


    @Test
    public void createTest() throws SQLException {
        Tenants created = tenants;

        UUID tenantId = tenantUseCase.create(created);
        created.setId(tenantId);
        Tenants get = tenantUseCase.get(created);
        assertEquals(created.getEmail(), get.getEmail());
    }

    @Test
    public void getTest() throws SQLException {
        Tenants createdTenant = tenants;
        tenantUseCase.create(createdTenant);
        Tenants tenant = tenantUseCase.get(createdTenant);
        Assertions.assertEquals(tenant.getEmail(), createdTenant.getEmail());
    }


    @Test
    public void getAllTest(){
        List<Tenants> listTenant = tenantUseCase.get();
        Assert.assertTrue(listTenant.size() > 0);
    }

    @Test
    public void deleteTest() throws SQLException {
        UUID tenantId = tenantUseCase.create(tenants);
        tenantUseCase.delete(tenantId);
        Tenants getTenant = tenantUseCase.get(tenants);
        Assert.assertNull(getTenant);


    }



    @Test
    public void updateTest() throws SQLException {

        UUID tenantId = tenantUseCase.create(tenants);
        Tenants createTenant = tenants;
        createTenant.setId(tenantId);
        tenantUseCase.update(createTenant);
        Tenants getTenant = tenantUseCase.get(createTenant);
        Assertions.assertEquals(createTenant.getName(), getTenant.getName());
        Assertions.assertEquals(tenantId, getTenant.getId());
    }

}
