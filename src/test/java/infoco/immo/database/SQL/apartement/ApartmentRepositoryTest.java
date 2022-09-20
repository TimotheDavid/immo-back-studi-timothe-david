package infoco.immo.database.SQL.apartement;

import infoco.immo.ObjectTesting.appartment.ApartmentObjectTest;
import infoco.immo.ObjectTesting.rent.RentObjectTest;
import infoco.immo.core.Apartment;
import infoco.immo.core.Rent;
import infoco.immo.database.SQL.appartment.ApartmentRepository;
import infoco.immo.database.SQL.rent.RentRepository;
import org.junit.jupiter.api.*;
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
class ApartmentRepositoryTest {


    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DataSource dataSource;

    @Autowired
    ApartmentRepository apartmentRepository;

    @Autowired
    RentRepository rentRepository;

    private final Apartment apartment = ApartmentObjectTest.getApartment();

    private final Rent rent = RentObjectTest.getRent();
    @AfterEach
    public void afterEach(){
        tearDownDatabase();
    }

    @BeforeEach
    public  void beforeEach(){
        apartment.setId(UUID.randomUUID());
        apartmentRepository.create(apartment);
    }

    @Test
    void createTest(){
        apartment.setId(UUID.randomUUID());
        apartmentRepository.create(apartment);
        Apartment apartmentObject = apartmentRepository.get(apartment);
        Assertions.assertEquals(apartment.getId(), apartmentObject.getId());
    }

    @Test
    void updateTest() {
        apartment.setCity("Strasbourg");
        apartmentRepository.update(apartment);
        Apartment apartmentObject = apartmentRepository.get(apartment);
        Assertions.assertEquals(apartment.getCity(), apartmentObject.getCity());
    }

    @Test
    void getTest(){
        Apartment apartmentObject = apartmentRepository.get(apartment);
        Assertions.assertEquals(apartmentObject.getAddress(), apartment.getAddress());
    }

    @Test
    void getAllTest(){
        generate();
        List<Apartment> apartmentList = apartmentRepository.get();
        Assertions.assertEquals(10,apartmentList.size());
    }

    @Test
    void deleteTest(){
        apartmentRepository.delete(apartment.getId());
        Apartment apartmentObject = apartmentRepository.get(apartment);
        Assertions.assertNull(apartmentObject);
    }

    @Test
    void patchWithRentTest(){
        rentRepository.create(rent);
        apartmentRepository.patchWithRent(apartment.getId(), rent.getId());
        Assertions.assertTrue(true);

    }

    private void generate(){
        int i = 1;
        while (i < 10){
            i++;
            beforeEach();
        }
    }

    private void  tearDownDatabase() {
        jdbcTemplate.execute("DELETE FROM immo.apartment");

    }
}
