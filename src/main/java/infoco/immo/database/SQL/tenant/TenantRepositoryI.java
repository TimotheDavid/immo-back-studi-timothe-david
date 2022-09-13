package infoco.immo.database.SQL.tenant;

import infoco.immo.core.Tenants;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface TenantRepositoryI {
    public void create(Tenants tenants) throws SQLException;
    public Tenants get(Tenants tenants);
    public void update(Tenants tenants);

    public void delete(UUID tenantId);

    public List<Tenants> get();
}
