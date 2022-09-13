package infoco.immo.http.rent;

import infoco.immo.core.Rent;
import infoco.immo.http.rent.dto.CreateRentDTO;
import infoco.immo.http.rent.dto.UpdateRentDTO;
import infoco.immo.http.rent.mapper.RentMappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/rent")
public class RentController {

    @Autowired
    RentService rentService;


    @PostMapping
    public void create(@RequestBody CreateRentDTO rentDTO){
        Rent rent = RentMappers.INSTANCE.createDTOToRent(rentDTO);
        rentService.create(rent);
    }

    @GetMapping(value = "/{uuid}")
    public Rent getOne(@PathVariable String uuid){
        Rent rent = Rent.builder().id(UUID.fromString(uuid)).build();
        return rentService.get(rent);
    }

    @GetMapping
    public List<Rent> getAll() {
        return rentService.get();
    }

    @PutMapping
    public void update(@RequestBody UpdateRentDTO rentDTO){
        Rent rent = RentMappers.INSTANCE.updateRentToRent(rentDTO);
        rentService.update(rent);
    }

    @DeleteMapping(value = "/{uuid}")
    public void delete(@PathVariable String uuid) {
        rentService.delete(UUID.fromString(uuid));
    }
}
