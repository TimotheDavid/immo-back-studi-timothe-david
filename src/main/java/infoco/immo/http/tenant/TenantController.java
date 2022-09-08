package infoco.immo.http.tenant;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TenantController {

    @Autowired
    TenantService tenantService;

}
