package infoco.immo.usecase.apartment;

import infoco.immo.ObjectTesting.appartment.ApartmentObjectTest;
import infoco.immo.configuration.BeanConfiguration;
import infoco.immo.configuration.PostgresDataConfigurationTest;
import infoco.immo.core.Apartment;
import infoco.immo.database.SQL.appartment.ApartmentRepository;
import infoco.immo.usecase.appartment.ApartmentUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;


@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ImportAutoConfiguration(exclude = PostgresDataConfigurationTest.class)
public class ApartmentUseCaseTest {

    private final Apartment apartment = ApartmentObjectTest.getApartment();

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    BeanConfiguration beanConfiguration;

    @Autowired
    ApartmentRepository apartmentRepository;

    @Test
    public void createTest(){
        beanConfiguration.apartmentUseCase().create(apartment);
        final Apartment apartmentObject = apartmentRepository.get(apartment);
        Assertions.assertEquals(apartmentObject.getAddress(), apartment.getAddress());
    }

    @Test
    public void getTest(){
        beanConfiguration.apartmentUseCase().create(apartment);
        final Apartment apartmentObject = beanConfiguration.apartmentUseCase().get(apartment);
        Assertions.assertEquals(apartment.getAddress(), apartmentObject.getAddress());
    }

    @Test
    public void updateTest(){
        UUID apartmentId =  beanConfiguration.apartmentUseCase().create(apartment);
        Apartment apartmentCreation = apartment;
        apartmentCreation.setId(apartmentId);
        beanConfiguration.apartmentUseCase().update(apartment);
        Apartment apartmentObject = apartmentRepository.get(apartmentCreation);
        Assertions.assertEquals(apartmentCreation.getAddress(), apartmentObject.getAddress());
    }

    @Test
    public void deleteTest(){
        UUID apartmentId =  beanConfiguration.apartmentUseCase().create(apartment);
        apartmentRepository.delete(apartmentId);
        Apartment apartmentObject = new Apartment();
        apartmentObject.setId(apartmentId);
        Apartment apartmentGet = apartmentRepository.get(apartmentObject);
        Assertions.assertNull(apartmentGet);
    }

    @Test
    public void getAllTest(){
        beanConfiguration.apartmentUseCase().create(apartment);
        final List<Apartment> apartmentObject = beanConfiguration.apartmentUseCase().get();
        Assertions.assertTrue(apartmentObject.size() > 0);
    }

    @Test
    public void delete() {
        apartment.setId(UUID.randomUUID());
        apartmentRepository.create(apartment);
        beanConfiguration.apartmentUseCase().delete(apartment.getId());


    }
}
