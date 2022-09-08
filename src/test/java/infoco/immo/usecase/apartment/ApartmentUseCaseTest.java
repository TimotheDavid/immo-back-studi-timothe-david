package infoco.immo.usecase.apartment;

import infoco.immo.ObjectTesting.appartment.ApartmentObjectTest;
import infoco.immo.ObjectTesting.tenants.TenantsObjectTest;
import infoco.immo.configuration.PostgresDataConfigurationTest;
import infoco.immo.core.Apartment;
import infoco.immo.core.Tenants;
import infoco.immo.database.appartment.ApartmentRepository;
import infoco.immo.database.tenant.TenantRepository;
import infoco.immo.usecase.appartment.ApartmentUseCase;
import infoco.immo.usecase.appartment.ApartmentUseCaseI;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class ApartmentUseCaseTest {

    private final Apartment apartment = ApartmentObjectTest.getApartment();

    private final ApartmentUseCase apartmentUseCase = apartmentUseCase();

    private static ApartmentUseCase apartmentUseCase() {

        final ApartmentRepository apartmentRepository = new ApartmentRepository();
        apartmentRepository.setDataSource(new PostgresDataConfigurationTest().dataSource());
        return new ApartmentUseCase(apartmentRepository);
    }


    @BeforeAll
    static void beforEAll(){
        apartmentUseCase();
    }

    @Test
    public void createTest(){
        apartmentUseCase.create(apartment);
        final Apartment apartmentObject = apartmentUseCase.get(apartment);
        assertEquals(apartmentObject.getAddress(), apartment.getAddress());
    }

    @Test
    public void updateTest(){
        UUID apprtementId =  apartmentUseCase.create(apartment);
        Apartment apartmentcreation = apartment;
        apartmentcreation.setId(apprtementId);
        apartmentUseCase.update(apartment);
        Apartment apartmentObject = apartmentUseCase.get(apartmentcreation);
        assertEquals(apartmentcreation.getAddress(), apartmentObject.getAddress());
    }

    @Test
    public void deleteTest(){
        UUID apartmentId =  apartmentUseCase.create(apartment);
        apartmentUseCase.delete(apartmentId);
        Apartment apartmentObject = new Apartment();
        apartmentObject.setId(apartmentId);
        apartmentUseCase.delete(apartmentObject.getId());

        Apartment apartmentGet = apartmentUseCase.get(apartmentObject);

        assertNull(apartmentGet);



    }

}
