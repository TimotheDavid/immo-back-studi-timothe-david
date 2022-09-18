package infoco.immo.http.tenant;

import infoco.immo.configuration.BeanConfiguration;
import infoco.immo.configuration.DatabaseConfiguration;
import infoco.immo.core.Tenants;
import infoco.immo.database.SQL.tenant.TenantRepository;
import infoco.immo.usecase.tenant.TenantUseCase;
import infoco.immo.usecase.tenant.TenantUseCaseI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Service
public class TenantService implements TenantUseCaseI {


    @Autowired
    BeanConfiguration beanConfiguration;
    public List<Tenants> get(){
        return beanConfiguration.tenantUseCase().get();
    }
    @Override
    public void  create(Tenants tenant) throws SQLException {
        beanConfiguration.tenantUseCase().create(tenant);
    }

    @Override
    public Tenants get(Tenants tenant) {
        return beanConfiguration.tenantUseCase().get(tenant);
    }

    @Override
    public void  update(Tenants tenants) {
        beanConfiguration.tenantUseCase().update(tenants);
    }

    @Override
    public  void delete(UUID tenantUUId){
        beanConfiguration.tenantUseCase().delete(tenantUUId);

    }
}
