package infoco.immo.database.SQL.rent;

import infoco.immo.ObjectTesting.rent.RentObjectTest;
import infoco.immo.core.Rent;
import org.apache.commons.lang3.RandomStringUtils;
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
class RentRepositoryTest {


    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DataSource dataSource;

    @Autowired
    RentRepository rentRepository;

    private final Rent rent = RentObjectTest.getRent();
    @BeforeEach
    public void beforeEach(){
        rent.setId(UUID.randomUUID());
        rentRepository.create(rent);
    }

    @AfterEach
    public void tearDownDatabase(){
        jdbcTemplate.execute("DELETE  FROM immo.payment");
        jdbcTemplate.execute("DELETE FROM immo.rent");

    }
    @Test
    void createTest(){
        rent.setId(UUID.randomUUID());
        rentRepository.create(rent);
        Rent rentObject = rentRepository.get(rent);
        Assertions.assertEquals(rent.getAmount(), rentObject.getAmount());
    }

    @Test
    void getTest(){
        Rent renObject = rentRepository.get(rent);
        Assertions.assertEquals(rent.getAmount(), renObject.getAmount());
    }

    @Test
    void updateTest() {
        rent.setDescriptionIn(RandomStringUtils.random(20));
        rentRepository.update(rent);
        Rent rentObject = rentRepository.get(rent);
        Assertions.assertEquals(rent.getDescriptionIn(), rentObject.getDescriptionIn());
    }

    @Test
    void deleteTest(){
        rentRepository.delete(rent.getId());
        Rent rentObject = rentRepository.get(rent);
        Assertions.assertNull(rentObject);
    }

    @Test
    void getAllTest(){
        generate();
        List<Rent> rentList  =  rentRepository.get();
        Assertions.assertEquals(10, rentList.size());
    }

    private void generate(){
        int i = 1;
        while (i < 10) {
            beforeEach();
            i++;
        }

    }

}
