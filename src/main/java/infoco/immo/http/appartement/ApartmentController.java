package infoco.immo.http.appartement;


import infoco.immo.core.Apartment;
import infoco.immo.http.appartement.apartmentMapper.ApartmentMapper;
import infoco.immo.http.appartement.dto.CreateApartmentDTO;
import infoco.immo.http.appartement.dto.UpdateApartmentDTO;
import infoco.immo.http.appartement.response.ApartmentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping( "/api/apartment")
@CrossOrigin(origins = "*")
public class ApartmentController {

    @Autowired
    AppartmentService appartmentService;

    @PostMapping
    public void create(@RequestBody CreateApartmentDTO createApartmentDTO){
        Apartment apartment = ApartmentMapper.INSTANCE.CreateDTOtoDomain(createApartmentDTO);
        appartmentService.create(apartment);
    }

    @GetMapping(value = "/{uuid}")
    public ResponseEntity<ApartmentResponse> getOne(@PathVariable String uuid){
        Apartment apartment = Apartment.builder().id(UUID.fromString(uuid)).build();
        return ResponseEntity.ok(ApartmentMapper.INSTANCE.domainToResponse(appartmentService.get(apartment)));
    }

    @GetMapping
    public ResponseEntity<List<ApartmentResponse>> getAll(){
        return ResponseEntity.ok(appartmentService.get().stream().map(ApartmentMapper.INSTANCE::domainToResponse).collect(Collectors.toList()));
    }

    @PutMapping
    public ResponseEntity update(@RequestBody UpdateApartmentDTO updateApartmentDTO){
        Apartment apartment = ApartmentMapper.INSTANCE.updateDTOToDomain(updateApartmentDTO);
        appartmentService.update(apartment);
        return ResponseEntity.ok().build();

    }

    @DeleteMapping(value = "/{uuid}")
    public ResponseEntity delete(@PathVariable String uuid) {
        appartmentService.delete(UUID.fromString(uuid));
        return ResponseEntity.ok().build();
    }

}
