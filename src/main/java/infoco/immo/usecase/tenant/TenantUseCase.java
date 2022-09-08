package infoco.immo.usecase.tenant;

import infoco.immo.core.Tenants;
import infoco.immo.database.tenant.TenantRepositoryI;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.sql.SQLException;
import java.util.UUID;

@RequiredArgsConstructor
public class TenantUseCase {

    public final TenantRepositoryI tenantRepositoryI;

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


}
