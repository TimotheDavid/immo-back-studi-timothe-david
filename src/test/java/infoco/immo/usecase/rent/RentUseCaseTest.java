package infoco.immo.usecase.rent;


import infoco.immo.ObjectTesting.appartment.ApartmentObjectTest;
import infoco.immo.ObjectTesting.rent.RentObjectTest;
import infoco.immo.ObjectTesting.tenants.TenantsObjectTest;
import infoco.immo.configuration.PostgresDataConfigurationTest;
import infoco.immo.core.Apartment;
import infoco.immo.core.Rent;
import infoco.immo.core.Tenants;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class RentUseCaseTest {


    private final Rent rent = RentObjectTest.getRent();

    private final Apartment apartment = ApartmentObjectTest.getApartment();

    private final Tenants  tenant = TenantsObjectTest.getTenant();

    private final RentUseCase rentUseCase = beforeAllRent();

    private final ApartmentRepository apartmentRepository = beforeAllApartment();

    private  final TenantRepository tenantRepository = beforeAllTenant();
    static RentUseCase beforeAllRent() {
        RentRepository rentRepository = new RentRepository();
        rentRepository.setDataSource(new PostgresDataConfigurationTest().dataSource());
        return new RentUseCase(rentRepository);
    }

    static ApartmentRepository beforeAllApartment() {
        ApartmentRepository apartmentRepository = new ApartmentRepository();
        apartmentRepository.setDataSource(new PostgresDataConfigurationTest().dataSource());
        return apartmentRepository;
    }
    static TenantRepository beforeAllTenant() {
        TenantRepository tenantRepository = new TenantRepository();
        tenantRepository.setDataSource(new PostgresDataConfigurationTest().dataSource());
        return tenantRepository;
    }



    @BeforeAll
    static void beforeAll() {
        beforeAllRent();
    }

    @Test
    public void createTest() throws SQLException {
        Rent rentObject = rent;
        Apartment apartmentObject;
        apartmentObject = apartment;
        apartment.setId(UUID.randomUUID());
        apartmentRepository.create(apartment);
        tenant.setId(UUID.randomUUID());
        tenantRepository.create(tenant);
        rentObject.setApartmentId(apartment.getId());
        rentObject.setTenantsId(tenant.getId());
        UUID rentId = rentUseCase.create(rentObject);
        rentObject.setId(rentId);
        rentObject.setApartmentId(apartmentObject.getId());
        Rent rentReturn = rentUseCase.get(rentObject);

        assertEquals(rent.getDeposit(), rentReturn.getDeposit());
    }

    @Test
    public void getTest() {
        Rent rentObject = rent;
        UUID rentId = rentUseCase.create(rentObject);

        rentObject.setId(rentId);
        Rent rentReturn = rentUseCase.get(rentObject);

        assertEquals(rentObject.getDescriptionIn(), rentReturn.getDescriptionIn());
    }

    @Test
    public void updateTest() {
        Rent rentObject = rent;
        UUID rentId = rentUseCase.create(rentObject);
        apartment.setId(UUID.randomUUID());
        apartmentRepository.create(apartment);

        rentObject.setId(rentId);
        rentUseCase.update(rentObject);
        Rent rentUpdated = rentUseCase.get(rentObject);
        assertEquals(rentUpdated.getId(), rentObject.getId());
        assertEquals(rentUpdated.getRentAmount(), rentObject.getRentAmount());
    }

    @Test
    public void deleteTest() throws SQLException {
        Rent rentObject = rent;
        Apartment apartmentObject;
        apartmentObject = apartment;
        apartment.setId(UUID.randomUUID());
        apartmentRepository.create(apartment);
        tenant.setId(UUID.randomUUID());
        tenantRepository.create(tenant);
        rentObject.setApartmentId(apartment.getId());
        rentObject.setTenantsId(tenant.getId());
        UUID rentId = rentUseCase.create(rentObject);
        rentUseCase.delete(rentId);
        assertNull(rentUseCase.get(rentObject));
    }


}
