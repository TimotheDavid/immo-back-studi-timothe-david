package infoco.immo.http.tenant;


import infoco.immo.core.Tenants;
import infoco.immo.http.tenant.dto.CreateTenantDTO;
import infoco.immo.http.tenant.dto.UpdateTenantDTO;
import infoco.immo.http.tenant.mapper.TenantMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tenant")
public class TenantController {

    @Autowired
    TenantService tenantService;


    @PostMapping
    public void createTenant(@RequestBody CreateTenantDTO createTenant) throws SQLException {
        Tenants tenants = TenantMapper.INSTANCE.tenantToCreateDTO(createTenant);
        tenantService.create(tenants);


    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Tenants> getTenant(@PathVariable(value ="uuid") String uuid) {
        Tenants tenant = Tenants.builder().id(UUID.fromString(uuid)).build();
        Tenants  tenantObject = tenantService.get(tenant);
        return new ResponseEntity<>(tenantObject, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Tenants>> getTenant() {
        List<Tenants> tenantObject = tenantService.get();
        return new ResponseEntity<>(tenantObject, HttpStatus.OK);
    }



    @PatchMapping
    public void updateTenant(@RequestBody UpdateTenantDTO updateTenantDTO){
        Tenants tenant = TenantMapper.INSTANCE.updateDTOToTenant(updateTenantDTO);
        tenantService.update(tenant);
    }

    @DeleteMapping(value = "/{uuid}")
    public void deleteTenant(@PathVariable("uuid") String uuid){
        tenantService.delete(UUID.fromString(uuid));
    }



}
