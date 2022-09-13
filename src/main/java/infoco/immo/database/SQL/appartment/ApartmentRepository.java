package infoco.immo.database.SQL.appartment;

import infoco.immo.core.Apartment;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.UUID;

public class ApartmentRepository implements ApartmentRepositoryI {


    private JdbcTemplate db;

    public void setDataSource(DataSource dataSource) {
        db = new JdbcTemplate(dataSource);
    }

    @Override
    public void create(Apartment apartment) {
        final String SQL = "INSERT INTO immo.apartment(uuid, address, complement, city, postal_code) values (?,?,?,?,?)";
        db.update(SQL, ps -> {
            int nthPlace = 1;
            ps.setObject(nthPlace++, apartment.getId());
            ps.setString(nthPlace++, apartment.getAddress());
            ps.setString(nthPlace++, apartment.getComplement());
            ps.setString(nthPlace++, apartment.getCity());
            ps.setString(nthPlace++, apartment.getPostalCode());
        });
    }

    @Override
    public Apartment get(Apartment apartment) {
        final String SQL = "SELECT * FROM immo.apartment WHERE uuid = ? ";
        return db.query(SQL, new ApartmentMapper(), apartment.getId()).stream().findFirst().orElse(null);
    }

    @Override
    public List<Apartment> get() {
        final String SQL = "SELECT * FROM immo.apartment";
        return db.query(SQL,new ApartmentMapper());
    }

    @Override
    public void update(Apartment apartment) {
        final String SQL = "UPDATE immo.apartment SET address = ?, complement = ?, city = ?, postal_code = ?, charge = ?, rent = ?, deposit = ? WHERE  uuid = ?";
        db.update(SQL, ps -> {
            int nthPlace = 1;
            ps.setString(nthPlace++,apartment.getAddress());
            ps.setString(nthPlace++, apartment.getComplement());
            ps.setString(nthPlace++, apartment.getCity());
            ps.setString(nthPlace++, apartment.getPostalCode());
            ps.setDouble(nthPlace++, apartment.getCharge());
            ps.setDouble(nthPlace++, apartment.getRent());
            ps.setDouble(nthPlace++, apartment.getDeposit());
            ps.setObject(nthPlace++, apartment.getId());
        });
    }

    @Override
    public void delete(UUID apartmentId) {
        final String SQL = "DELETE FROM immo.apartment WHERE uuid = ?";
        db.update(SQL, apartmentId);
    }


    @Override
    public PaymentDataByAppartment getPaymentByApartment(UUID apartmentId) {
        return null;
    }
}
