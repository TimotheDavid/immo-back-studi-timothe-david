package infoco.immo.usecase.tenant;

import infoco.immo.core.Tenants;

import java.util.UUID;

public interface TenantUseCaseI {
    public UUID create(Tenants tenant);
    public Tenants get(Tenants tenant);
    public UUID update(Tenants tenants);
}
