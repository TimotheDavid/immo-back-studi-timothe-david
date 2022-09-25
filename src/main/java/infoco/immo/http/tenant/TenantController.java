package infoco.immo.http.tenant;


import infoco.immo.core.Tenants;
import infoco.immo.http.tenant.dto.CreateTenantDTO;
import infoco.immo.http.tenant.dto.UpdateTenantDTO;
import infoco.immo.http.tenant.mapper.TenantMapper;
import infoco.immo.http.tenant.response.TenantResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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
    public ResponseEntity<TenantResponse> getTenant(@PathVariable(value ="uuid") String uuid) {
        Tenants tenant = Tenants.builder().id(UUID.fromString(uuid)).build();
        Tenants  tenantObject = tenantService.get(tenant);
        return new ResponseEntity<>(TenantMapper.INSTANCE.domainToResponse(tenantObject), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<TenantResponse>> getTenant() {
        List<TenantResponse> tenantObject = Optional.of(tenantService.get().stream().map(TenantMapper.INSTANCE::domainToResponse).collect(Collectors.toList())).orElse(null);
        return new ResponseEntity<>(tenantObject, HttpStatus.OK);
    }



    @PutMapping
    public void updateTenant(@RequestBody UpdateTenantDTO updateTenantDTO){
        Tenants tenant = TenantMapper.INSTANCE.updateDTOToTenant(updateTenantDTO);
        tenantService.update(tenant);
    }

    @DeleteMapping(value = "/{uuid}")
    public void deleteTenant(@PathVariable("uuid") String uuid){
        tenantService.delete(UUID.fromString(uuid));
    }

    @GetMapping(value = "/sheet")
    @ResponseBody
    public ResponseEntity getReceipt(@RequestParam(required = true) String  rent) throws IOException {
        InputStream inputStream = tenantService.getBalanceSheet(rent);

        if(inputStream == null){
            return ResponseEntity.ok().build();
        }

        ByteArrayResource resource = new ByteArrayResource(inputStream.readAllBytes());

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + "rentReceipt.pdf")
                .contentLength(resource.contentLength())
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }



}
