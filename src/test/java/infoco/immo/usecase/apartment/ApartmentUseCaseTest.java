package infoco.immo.usecase.apartment;

import infoco.immo.ObjectTesting.appartment.ApartmentObjectTest;
import infoco.immo.configuration.BeanConfiguration;
import infoco.immo.core.Apartment;
import infoco.immo.database.SQL.appartment.ApartmentRepository;
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
import java.util.List;
import java.util.UUID;


@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ImportAutoConfiguration
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

    @BeforeEach
    void beforeEach(){
        apartment.setId(UUID.randomUUID());
        apartmentRepository.create(apartment);
    }

    @AfterEach
    void afterEach(){
        jdbcTemplate.execute("DELETE FROM immo.apartment");
    }

    @Test
    void createTest(){
        UUID apartmentId = beanConfiguration.apartmentUseCase().create(apartment);
        Apartment apartmentCreated = Apartment.builder().id(apartmentId).build();
        final Apartment apartmentObject = apartmentRepository.get(apartmentCreated);
        Assertions.assertEquals(apartmentObject.getAddress(), apartment.getAddress());
    }

    @Test
    void getTest(){
        final Apartment apartmentObject = beanConfiguration.apartmentUseCase().get(apartment);
        Assertions.assertEquals(apartment.getAddress(), apartmentObject.getAddress());
    }

    @Test
    void updateTest(){
        apartment.setCity("strasbourg");
        beanConfiguration.apartmentUseCase().update(apartment);
        Apartment apartmentObject = apartmentRepository.get(apartment);
        Assertions.assertEquals(apartment.getCity(), apartmentObject.getCity());
    }

    @Test
    void deleteTest(){
        beanConfiguration.apartmentUseCase().delete(apartment.getId());
        Apartment apartmentGet = apartmentRepository.get(apartment);
        Assertions.assertNull(apartmentGet);
    }

    @Test
    void getAllTest(){
        generate();
        final List<Apartment> apartmentObject = beanConfiguration.apartmentUseCase().get();
        Assertions.assertEquals(10,apartmentObject.size());
    }



    private void generate(){
        int i = 1;
        while (i < 10){
            beforeEach();
            i++;
        }


    }
}
