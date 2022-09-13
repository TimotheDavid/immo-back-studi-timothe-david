package infoco.immo.http.tenant;

import infoco.immo.configuration.DatabaseConfiguration;
import infoco.immo.core.Tenants;
import infoco.immo.database.SQL.tenant.TenantRepository;
import infoco.immo.usecase.tenant.TenantUseCase;
import infoco.immo.usecase.tenant.TenantUseCaseI;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Service
public class TenantService implements TenantUseCaseI {

    private TenantUseCase tenantUseCase(){
        TenantRepository tenantRepository = new TenantRepository();
        tenantRepository.setDataSource(new DatabaseConfiguration().dataSource());
        return new TenantUseCase(tenantRepository);
    }

    public List<Tenants> get(){
        return tenantUseCase().get();
    }
    @Override
    public void  create(Tenants tenant) throws SQLException {
        tenantUseCase().create(tenant);
    }

    @Override
    public Tenants get(Tenants tenant) {
        return tenantUseCase().get(tenant);
    }

    @Override
    public void  update(Tenants tenants) {
        tenantUseCase().update(tenants);
    }

    @Override
    public  void delete(UUID tenantUUId){
        tenantUseCase().delete(tenantUUId);

    }
}
