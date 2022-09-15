package infoco.immo.http.rent;

import infoco.immo.core.Rent;
import infoco.immo.http.rent.dto.CreateRentDTO;
import infoco.immo.http.rent.dto.UpdateRentDTO;
import infoco.immo.http.rent.mapper.RentMappers;
import infoco.immo.http.rent.response.RentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
    public ResponseEntity<RentResponse> getOne(@PathVariable String uuid){
        Rent rent = Rent.builder().id(UUID.fromString(uuid)).build();
        return new ResponseEntity<>(RentMappers.INSTANCE.domaineToResponse(rentService.get(rent)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<RentResponse>> getAll() {
        return  ResponseEntity.ok(rentService.get().stream().map(RentMappers.INSTANCE::domaineToResponse).collect(Collectors.toList()));
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
