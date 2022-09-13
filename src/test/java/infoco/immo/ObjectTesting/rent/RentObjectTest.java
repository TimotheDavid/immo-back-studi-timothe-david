package infoco.immo.ObjectTesting.rent;

import com.github.javafaker.Faker;
import infoco.immo.ObjectTesting.appartment.ApartmentObjectTest;
import infoco.immo.ObjectTesting.tenants.TenantsObjectTest;
import infoco.immo.configuration.PostgresDataConfigurationTest;
import infoco.immo.core.Apartment;
import infoco.immo.core.Rent;
import infoco.immo.core.Tenants;
import infoco.immo.database.SQL.appartment.ApartmentRepository;
import infoco.immo.database.SQL.rent.RentRepository;
import infoco.immo.database.SQL.tenant.TenantRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class RentObjectTest {

    private final Faker faker = new Faker();

    private final Apartment apartment = ApartmentObjectTest.getApartment();
    private final Tenants tenant = TenantsObjectTest.getTenant();

    private final RentRepository rentRepository = rentRepository();

    private RentRepository rentRepository() {
        RentRepository rentRepository = new RentRepository();
        rentRepository.setDataSource(new PostgresDataConfigurationTest().dataSource());
        return rentRepository;
    }


    private final TenantRepository tenantRepository = tenantRepository();

    private TenantRepository tenantRepository() {
        TenantRepository tenantRepository = new TenantRepository();
        tenantRepository.setDataSource(new PostgresDataConfigurationTest().dataSource());
        return tenantRepository;
    }

    private final ApartmentRepository apartmentRepository = apartmentRepository();

    private ApartmentRepository apartmentRepository(){
        ApartmentRepository apartmentRepository = new ApartmentRepository();
        apartmentRepository.setDataSource(new PostgresDataConfigurationTest().dataSource());
        return apartmentRepository;
    }


    private Rent createRent() {
        return Rent.builder()
                .id(UUID.randomUUID())
                .apartmentId(apartment.getId())
                .deposit((float) faker.number().numberBetween(0, 10000))
                .descriptionIn(faker.lorem().paragraphs(1).toString())
                .inDate(faker.date().past(100, TimeUnit.DAYS).toString())
                .descriptionOut(faker.lorem().paragraphs(1).toString())
                .outDate(faker.date().future(100, TimeUnit.DAYS).toString())
                .amount((float) faker.number().numberBetween(0, 10000))
                .agencyPourcent((float) 8)
                .tenantsId(tenant.getId()).build();
    }

    private UUID  generateRent() {
        Rent rentObject = createRent();
        apartment.setId(UUID.randomUUID());
        apartmentRepository.create(apartment);
        tenant.setId(UUID.randomUUID());
        tenantRepository.create(tenant);
        rentObject.setApartmentId(apartment.getId());
        rentObject.setTenantsId(tenant.getId());
        rentRepository.create(rentObject);
        return rentObject.getId();
    }

    private Rent  generateCreatedLineWthoutRent(){
        Rent rentObject = createRent();
        apartment.setId(UUID.randomUUID());
        apartmentRepository.create(apartment);
        tenant.setId(UUID.randomUUID());
        tenantRepository.create(tenant);
        rentObject.setApartmentId(apartment.getId());
        rentObject.setTenantsId(tenant.getId());
        return rentObject;
    }

    public static UUID  generateRentLine() {
        return  new RentObjectTest().generateRent();
    }

    public static Rent generateLineWitoutRent(){ return  new RentObjectTest().generateCreatedLineWthoutRent();}
    public static Rent getRent() {
        return new RentObjectTest().createRent();
    }
}
