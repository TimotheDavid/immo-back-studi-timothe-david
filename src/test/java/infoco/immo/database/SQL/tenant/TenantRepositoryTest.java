package infoco.immo.database.SQL.tenant;


import com.github.javafaker.Faker;
import infoco.immo.ObjectTesting.rent.RentObjectTest;
import infoco.immo.ObjectTesting.tenants.TenantsObjectTest;
import infoco.immo.configuration.PostgresDataConfigurationTest;
import infoco.immo.core.Rent;
import infoco.immo.core.Tenants;
import infoco.immo.database.SQL.rent.RentRepository;
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
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.util.List;
import java.util.UUID;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@ImportAutoConfiguration(exclude = PostgresDataConfigurationTest.class)
class TenantRepositoryTest {


    @Autowired
    JdbcTemplate jdbcTemplate;


    @Autowired
    Faker faker;

    @Autowired
    DataSource dataSource;

    @Autowired
    TenantRepository tenantRepository;

    @Autowired
    RentRepository rentRepository;

    private final Rent rent = RentObjectTest.getRent();

    private final Tenants tenants = TenantsObjectTest.getTenant();


    @BeforeEach
    public void beforeEach(){
        tenants.setEmail(faker.internet().emailAddress());
        tenants.setId(UUID.randomUUID());
        tenantRepository.create(tenants);
    }

    @AfterEach
    public void afterEach(){
        tearDown();
    }



    @Test
    void createTest(){
        tenants.setId(UUID.randomUUID());
        tenants.setEmail(faker.internet().emailAddress());
        tenantRepository.create(tenants);
        Tenants tenantsObject = tenantRepository.get(tenants);
        Assertions.assertEquals(tenants.getName(), tenantsObject.getName());
    }

    @Test
    void getTest(){
        Tenants tenantObject = tenantRepository.get(tenants);
        Assertions.assertEquals(tenants.getName(), tenantObject.getName());
    }

    @Test
    void getAllTest(){
        generate();
        List<Tenants> tenantsList = tenantRepository.get();
        Assertions.assertEquals(10,tenantsList.size());
    }

    @Test
    void deleteTest(){
        tenantRepository.delete(tenants.getId());
        Tenants tenantsObject = tenantRepository.get(tenants);
        Assertions.assertNull(tenantsObject);
    }

    @Test
    void patchWithRentTest(){
        rentRepository.create(rent);
        tenantRepository.patchWithRent(tenants.getId(), rent.getId());
        Tenants tenantsObject = tenantRepository.get(tenants);
        Assertions.assertNotNull(tenantsObject.getRent());
    }


    private void tearDown(){
        jdbcTemplate.execute("DELETE FROM immo.tenant");
    }

    private void generate(){
        int i = 1;
        while (i < 10){
            i++;
            beforeEach();

        }

    }



}
