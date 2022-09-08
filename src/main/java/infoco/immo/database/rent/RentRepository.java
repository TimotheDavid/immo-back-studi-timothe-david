package infoco.immo.database.rent;

import infoco.immo.core.Rent;
import infoco.immo.usecase.rent.RentResponse;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.UUID;

public class RentRepository implements RentRepositoryI {


    private JdbcTemplate db;

    public void setDataSource(DataSource dataSource) {
        db = new JdbcTemplate(dataSource);
    }

    @Override
    public void create(Rent rent) {
        final String SQL = " INSERT INTO immo.rent(uuid, rent, in_date, in_description, out_date, out_description, deposit, agency_pourcent, apartmentid, tenantid) VALUES(?,?,?,?,?,?,?,?,?,?) ";
        db.update(SQL, ps -> {
            int nthPlace = 1;
            ps.setObject(nthPlace++, rent.getId());
            ps.setFloat(nthPlace++, rent.getRentAmount());
            ps.setString(nthPlace++, rent.getInDate());
            ps.setString(nthPlace++, rent.getDescriptionIn());
            ps.setString(nthPlace++, rent.getOutDate());
            ps.setString(nthPlace++, rent.getDescriptionOut());
            ps.setFloat(nthPlace++, rent.getDeposit());
            ps.setFloat(nthPlace++, rent.getAgencyPourcent());
            ps.setObject(nthPlace++, rent.getApartmentId());
            ps.setObject(nthPlace++, rent.getTenantsId());
        });
    }

    @Override
    public Rent get(Rent rent) {
        final String SQL = "SELECT * FROM immo.rent WHERE uuid = ?";
        return db.query(SQL, new RentMapper(), rent.getId()).stream().findFirst().orElse(null);
    }

    @Override
    public void update(Rent rent) {
        final String SQL = "UPDATE immo.rent SET rent = ?, in_date = ?, in_description = ?, out_date = ? , out_description = ?, deposit = ?, agency_pourcent = ?, apartmentid = ?, tenantid = ? WHERE uuid = ? ";
        db.update(SQL, ps -> {
            int nthPlace = 0;
            ps.setFloat(nthPlace++, rent.getRentAmount());
            ps.setString(nthPlace++, rent.getInDate());
            ps.setString(nthPlace++, rent.getDescriptionIn());
            ps.setString(nthPlace++, rent.getOutDate());
            ps.setString(nthPlace++, rent.getDescriptionOut());
            ps.setFloat(nthPlace++, rent.getDeposit());
            ps.setFloat(nthPlace++, rent.getAgencyPourcent());
            ps.setObject(nthPlace++, rent.getApartmentId());
            ps.setObject(nthPlace++, rent.getTenantsId());
            ps.setObject(nthPlace++, rent.getId());
        });
    }

    @Override
    public void delete(UUID rentId) {
        final String SQL = "DELETE FROM immo.rent WHERE uuid = ? ";
        db.update(SQL, rentId);

    }
}
