package infoco.immo.database.tenant;

import infoco.immo.core.Tenants;

import java.util.UUID;

public interface TenantRepositoryI {
    public UUID create(Tenants tenants);
    public Tenants get(Tenants tenants);
    public UUID update(Tenants tenants);
}
