package infoco.immo.usecase.apartment;

import infoco.immo.ObjectTesting.appartment.ApartmentObjectTest;
import infoco.immo.configuration.BeanConfiguration;
import infoco.immo.configuration.PostgresDataConfigurationTest;
import infoco.immo.core.Apartment;
import infoco.immo.database.SQL.appartment.ApartmentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.util.List;
import java.util.UUID;


@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ImportAutoConfiguration(exclude = PostgresDataConfigurationTest.class)
class ApartmentUseCaseTest {

    private final Apartment apartment = ApartmentObjectTest.getApartment();

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DataSource dataSource;
    @Autowired
    BeanConfiguration beanConfiguration;

    @Autowired
    ApartmentRepository apartmentRepository;

    @Test
    void createTest(){
        beanConfiguration.apartmentUseCase().create(apartment);
        final Apartment apartmentObject = apartmentRepository.get(apartment);
        Assertions.assertEquals(apartmentObject.getAddress(), apartment.getAddress());
    }

    @Test
    void getTest(){
        beanConfiguration.apartmentUseCase().create(apartment);
        final Apartment apartmentObject = beanConfiguration.apartmentUseCase().get(apartment);
        Assertions.assertEquals(apartment.getAddress(), apartmentObject.getAddress());
    }

    @Test
    void updateTest(){
        UUID apartmentId =  beanConfiguration.apartmentUseCase().create(apartment);
        Apartment apartmentCreation = apartment;
        apartmentCreation.setId(apartmentId);
        beanConfiguration.apartmentUseCase().update(apartment);
        Apartment apartmentObject = apartmentRepository.get(apartmentCreation);
        Assertions.assertEquals(apartmentCreation.getAddress(), apartmentObject.getAddress());
    }

    @Test
    void deleteTest(){
        UUID apartmentId =  beanConfiguration.apartmentUseCase().create(apartment);
        apartmentRepository.delete(apartmentId);
        Apartment apartmentObject = new Apartment();
        apartmentObject.setId(apartmentId);
        Apartment apartmentGet = apartmentRepository.get(apartmentObject);
        Assertions.assertNull(apartmentGet);
    }

    @Test
    void getAllTest(){
        beanConfiguration.apartmentUseCase().create(apartment);
        final List<Apartment> apartmentObject = beanConfiguration.apartmentUseCase().get();
        Assertions.assertTrue(apartmentObject.size() > 0);
    }

    @Test
    void delete() {
        apartment.setId(UUID.randomUUID());
        apartmentRepository.create(apartment);
        beanConfiguration.apartmentUseCase().delete(apartment.getId());
        Apartment apartmentObject = apartmentRepository.get(apartment);
        Assertions.assertNull(apartmentObject);


    }
}
