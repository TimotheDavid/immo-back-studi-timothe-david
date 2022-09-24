package infoco.immo.database.SQL.tenant;

import infoco.immo.core.Tenants;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface TenantRepositoryI {
    void create(Tenants tenants) throws SQLException;
    public Tenants get(Tenants tenants);

    List<TenantBalanceSheet> TenantBalanceSheet(UUID tenantId);

    void update(Tenants tenants);

    void delete(UUID tenantId);

    List<Tenants> get();
}
