package infoco.immo.database.tenant;

import infoco.immo.core.Tenants;

import java.sql.SQLException;
import java.util.UUID;

public interface TenantRepositoryI {
    public void create(Tenants tenants) throws SQLException;
    public Tenants get(Tenants tenants);
    public void update(Tenants tenants);
}
