package infoco.immo.database.SQL.payment;

import infoco.immo.core.Payment;
import infoco.immo.usecase.payment.RentReceiptData;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.UUID;

public class PaymentRepository implements PaymentRepositoryI {


    private JdbcTemplate db;

    public void setDataSource(DataSource dataSource) {
        db = new JdbcTemplate(dataSource);
    }

    @Override
    public void create(Payment payment) {
        final String SQL = "INSERT INTO immo.payment(uuid, amount, date_payment, landlor_part, agency_part,paymentrentid, typepaymentid, originid) VALUES (?,?,?,?,?,?,?,?)";
        db.update(SQL, ps -> {
            int nthPlace = 1;
            ps.setObject(nthPlace++, payment.getId());
            ps.setFloat(nthPlace++, payment.getAmount());
            ps.setString(nthPlace++, payment.getDatePayment());
            ps.setFloat(nthPlace++, payment.getLandlorPart());
            ps.setFloat(nthPlace++, payment.getAgencyPart());
            ps.setObject(nthPlace++, payment.getRentId());
            ps.setObject(nthPlace++, payment.getTypePayment());
            ps.setObject(nthPlace++, payment.getOriginId());
        });
    }

    @Override
    public void createMappingRentPayment(Payment payment) {
        final String SQL = "INSERT INTO immo.payment_rent(uuid, rentid, paymentid) VALUES(?,?,?)";
        final UUID mappingUUID = UUID.randomUUID();
        db.update(SQL, ps -> {
            int nthPlace = 1;
            ps.setObject(nthPlace++, mappingUUID);
            ps.setObject(nthPlace++, payment.getRentId());
            ps.setObject(nthPlace++, payment.getId());
        });
    }

    @Override
    public Payment get(Payment payment) {
        final String SQL = "SELECT * FROM immo.payment WHERE uuid = ?";
        return db.query(SQL, new PaymentMapper(), payment.getId()).stream().findFirst().orElse(null);
    }

    @Override
    public void update(Payment payment) {
        final String SQL = "UPDATE immo.payment SET amount = ?, date_payment = ?, landlor_part = ?, agency_part = ?, paymentrentid = ?, typepaymentid WHERE uuid = ?";
        db.update(SQL, ps -> {
            int nthPlace = 1;
            ps.setFloat(nthPlace++, payment.getAmount());
            ps.setString(nthPlace++, payment.getDatePayment());
            ps.setFloat(nthPlace++, payment.getLandlorPart());
            ps.setFloat(nthPlace++, payment.getAgencyPart());
            ps.setObject(nthPlace++, payment.getRentId());
            ps.setObject(nthPlace++, payment.getTypePayment());
        });
    }

    @Override
    public void delete(UUID paymentId) {
        final String SQL = " DELETE FROM immo.payment WHERE uuid = ?";
        db.update(SQL, paymentId);
    }

    @Override
    public RentReceiptData generateRentReceipt(String from, String to, UUID rentId) {
        return null;
    }
}
