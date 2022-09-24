package infoco.immo.database.SQL.appartment;

import infoco.immo.core.Apartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class ApartmentRepository implements ApartmentRepositoryI {

    @Autowired
    private JdbcTemplate db;

    @Override
    public void create(Apartment apartment) {
        final String SQL = "INSERT INTO immo.apartment(uuid, address, complement, city, postal_code, charge, rent, deposit) values (?,?,?,?,?,?,?,?)";
        db.update(SQL, ps -> {
            int nthPlace = 1;
            ps.setObject(nthPlace++, apartment.getId());
            ps.setString(nthPlace++, apartment.getAddress());
            ps.setString(nthPlace++, apartment.getComplement());
            ps.setString(nthPlace++, apartment.getCity());
            ps.setString(nthPlace++, apartment.getPostalCode());
            ps.setFloat(nthPlace++, apartment.getCharge());
            ps.setFloat(nthPlace++, apartment.getRent());
            ps.setFloat(nthPlace++, apartment.getDeposit());
        });
    }



    @Override
    public Apartment get(Apartment apartment) {
        final String SQL = "SELECT * FROM immo.apartment WHERE uuid = ? AND deleted is false ";
        return db.query(SQL, new ApartmentMapper(), apartment.getId()).stream().findFirst().orElse(null);
    }

    @Override
    public List<Apartment> get() {
        final String SQL = "SELECT * FROM immo.apartment where deleted = false";
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
        final String SQL = "UPDATE  immo.apartment SET deleted = true  WHERE uuid = ?";
        db.update(SQL, apartmentId);
    }

}
