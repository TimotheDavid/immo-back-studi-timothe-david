package infoco.immo.database.payment;

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
    public UUID create(Payment payment) {
        return null;
    }

    @Override
    public Payment get(UUID paymentId) {
        return null;
    }

    @Override
    public void update(Payment payment) {

    }

    @Override
    public UUID delete(UUID paymentId) {
        return null;
    }

    @Override
    public RentReceiptData generateRentReceipt(String from, String to, UUID rentId) {
        return null;
    }
}
