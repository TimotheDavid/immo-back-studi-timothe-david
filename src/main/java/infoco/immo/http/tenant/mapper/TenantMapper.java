package infoco.immo.http.tenant.mapper;


import infoco.immo.core.Tenants;
import infoco.immo.http.tenant.dto.CreateTenantDTO;
import infoco.immo.http.tenant.dto.UpdateTenantDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TenantMapper {

    TenantMapper INSTANCE = Mappers.getMapper(TenantMapper.class);

    Tenants createDTOtoTenant(CreateTenantDTO createTenantDTO);

    Tenants updateDTOToTenant(UpdateTenantDTO updateTenantDTO);

    Tenants updatedtotoTenant(UpdateTenantDTO updateTenantDTO);

    CreateTenantDTO tenantToCreateTenantDto(Tenants tenants);

    Tenants tenantToCreateDTO(CreateTenantDTO createTenant);

     UpdateTenantDTO tenantToUpdate(Tenants tenants);
}
