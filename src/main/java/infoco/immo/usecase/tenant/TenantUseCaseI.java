package infoco.immo.usecase.tenant;

import infoco.immo.core.Tenants;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface TenantUseCaseI {
    public void  create(Tenants tenant) throws SQLException;
    public Tenants get(Tenants tenant);
    public void  update(Tenants tenants);

    public List<Tenants> get();

    void delete(UUID tenantUUId);
}
