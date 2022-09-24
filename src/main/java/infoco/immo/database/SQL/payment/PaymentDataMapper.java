package infoco.immo.database.SQL.payment;

import infoco.immo.core.FromType;
import infoco.immo.core.Origin;
import infoco.immo.core.TypePayment;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class PaymentDataMapper implements RowMapper<PaymentData> {
    @Override
    public PaymentData mapRow(ResultSet rs, int rowNum) throws SQLException {
        PaymentData paymentData = new PaymentData();
        paymentData.setDate_payment(rs.getString("date_payment"));
        paymentData.setId(rs.getObject("uuid", UUID.class));
        paymentData.setSens(rs.getBoolean("sens"));
        paymentData.setAmount(rs.getFloat("amount"));
        paymentData.setOrigin(Origin.valueOf(rs.getString("origin")));
        paymentData.setFirstName(rs.getString("firstname"));
        paymentData.setUsername(rs.getString("username"));
        paymentData.setType(TypePayment.valueOf(rs.getString("type")));
        paymentData.setFromType(FromType.valueOf(rs.getString("from_type")));
        return paymentData;
    }
}
