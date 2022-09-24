package infoco.immo.database.SQL.tenant;

import infoco.immo.core.FromType;
import infoco.immo.core.Origin;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TenantBalanceSheetMapper implements RowMapper<TenantBalanceSheet> {
    @Override
    public TenantBalanceSheet mapRow(ResultSet rs, int rowNum) throws SQLException {
        TenantBalanceSheet tenantBalanceSheet = new TenantBalanceSheet();
        tenantBalanceSheet.setAmount(rs.getFloat("amount"));
        tenantBalanceSheet.setSens(rs.getBoolean("sens"));
        tenantBalanceSheet.setFromType(FromType.valueOf(rs.getString("from_type")));
        tenantBalanceSheet.setDatePayment(rs.getString("date_payment"));
        tenantBalanceSheet.setOrigin(Origin.valueOf(rs.getString("origin")));
        tenantBalanceSheet.setRent(rs.getFloat("rent"));
        return tenantBalanceSheet;
    }
}
