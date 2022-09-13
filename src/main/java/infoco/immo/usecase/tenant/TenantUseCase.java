package infoco.immo.usecase.tenant;

import infoco.immo.core.Tenants;
import infoco.immo.database.SQL.tenant.TenantRepositoryI;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class TenantUseCase {

    private final TenantRepositoryI tenantRepositoryI;

    public TenantUseCase(TenantRepositoryI tenantRepositoryI){
        this.tenantRepositoryI = tenantRepositoryI;
    }
    public UUID create(Tenants tenants) throws SQLException {
        tenants.setId(UUID.randomUUID());
        this.tenantRepositoryI.create(tenants);
        return tenants.getId();
    }

    public Tenants get(Tenants tenants) {
        return tenantRepositoryI.get(tenants);
    }

    public void update(Tenants tenants) {
        tenantRepositoryI.update(tenants);
    }

    public void delete(UUID tenantId) {
        tenantRepositoryI.delete(tenantId);
    }
    public List<Tenants> get() {
        return tenantRepositoryI.get();
    }


}
