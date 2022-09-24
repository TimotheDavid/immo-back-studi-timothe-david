package infoco.immo.database.SQL.payment;

import infoco.immo.core.FromType;
import infoco.immo.core.RentReceiptData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RentReceiptDataMapper implements RowMapper<RentReceiptData> {
    @Override
    public RentReceiptData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final RentReceiptData rentReceiptData = new RentReceiptData();
        rentReceiptData.setAmount(rs.getFloat("amount"));
        rentReceiptData.setSens(rs.getBoolean("sens"));
        rentReceiptData.setDatePayment(rs.getString("date_payment"));
        rentReceiptData.setFromType(FromType.valueOf(rs.getString("from_type")));
        return rentReceiptData;
    }
}
