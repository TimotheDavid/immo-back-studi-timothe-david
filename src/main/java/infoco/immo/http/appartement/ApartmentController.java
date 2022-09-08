package infoco.immo.http.appartement;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApartmentController {

    @Autowired
    AppartmentService appartmentService;
}
