package infoco.immo.http.rent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RentController {

    @Autowired
    RentService rentService;

}
