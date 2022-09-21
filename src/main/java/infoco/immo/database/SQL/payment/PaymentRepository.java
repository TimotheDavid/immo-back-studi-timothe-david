package infoco.immo.database.SQL.payment;

import infoco.immo.core.Payment;
import infoco.immo.core.RentReceiptData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public class PaymentRepository implements PaymentRepositoryI {


    @Autowired
    private JdbcTemplate db;

    @Override
    public void create(Payment payment) {
        final String SQL = "INSERT INTO immo.payment(uuid, amount, date_payment, landlor_part, agency_part, sens,rentid, type, origin) VALUES (?,?,?,?,?,?,?,CAST(? AS immo.type_to_pay),CAST(? AS immo.origin))";
        db.update(SQL, ps -> {
            int nthPlace = 1;
            ps.setObject(nthPlace++, payment.getId());
            ps.setFloat(nthPlace++, payment.getAmount());
            ps.setString(nthPlace++, payment.getDatePayment());
            ps.setFloat(nthPlace++, payment.getLandlorPart());
            ps.setFloat(nthPlace++, payment.getAgencyPart());
            ps.setBoolean(nthPlace++, payment.getSens());
            ps.setObject(nthPlace++, payment.getRentId());
            ps.setString(nthPlace++, payment.getTypePayment().toString());
            ps.setString(nthPlace++, payment.getOrigin().toString());
        });
    }


    @Override
    public Payment get(UUID paymentId) {
        final String SQL = "SELECT * FROM immo.payment WHERE uuid = ?";
        return db.query(SQL, new PaymentMapper(), paymentId).stream().findFirst().orElse(null);
    }

    @Override
    public List<Payment> get() {
        final String SQL = "SELECT * FROM immo.payment";
        return db.query(SQL, new PaymentMapper());
    }

    @Override
    public void update(Payment payment) {
        final String SQL = "UPDATE immo.payment SET amount = ?, date_payment = ?, landlor_part = ?, agency_part = ? WHERE uuid = ?";
        db.update(SQL, ps -> {
            int nthPlace = 1;
            ps.setFloat(nthPlace++, payment.getAmount());
            ps.setString(nthPlace++, payment.getDatePayment());
            ps.setFloat(nthPlace++, payment.getLandlorPart());
            ps.setFloat(nthPlace++, payment.getAgencyPart());
            ps.setObject(nthPlace++, payment.getId());
        });
    }

    @Override
    public void delete(UUID paymentId) {
        final String SQL = " DELETE FROM immo.payment WHERE uuid = ?";
        db.update(SQL, paymentId);
    }

    @Override
    public List<RentReceiptData> generateRentReceipt(String from, String to, String tenantId) {
        final String SQL = " select amount, sens,p.date_payment, tenantid from immo.payment p\n" +
                "    join immo.rent on p.rentid = rent.uuid\n" +
                "    join immo.tenant on rent.tenantid = tenant.uuid\n" +
                "    join immo.apartment a on rent.apartmentid = a.uuid\n" +
                "    where rent.uuid = ?";

        return db.query(SQL, new RentReceiptDataMapper(), UUID.fromString(tenantId));
    }
}
