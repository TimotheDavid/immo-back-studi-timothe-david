package infoco.immo.database.SQL.payment;

import infoco.immo.core.Payment;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class PaymentMapper implements RowMapper<Payment> {

    @Override
    public Payment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Payment payment = new Payment();
        payment.setRentId(rs.getObject("paymentrentid", UUID.class));
        payment.setDatePayment(rs.getString("date_payment"));
        payment.setTypePayment(rs.getObject("typepaymentid", UUID.class));
        payment.setAmount(rs.getFloat("amount"));
        payment.setAgencyPart(rs.getFloat("agency_part"));
        payment.setLandlorPart(rs.getFloat("landlor_part"));
        return payment;
    }
}
