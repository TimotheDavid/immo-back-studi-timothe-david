package infoco.immo.http.appartement;


import infoco.immo.core.Apartment;
import infoco.immo.http.appartement.apartmentMapper.ApartmentMapper;
import infoco.immo.http.appartement.dto.CreateApartmentDTO;
import infoco.immo.http.appartement.dto.UpdateApartmentDTO;
import infoco.immo.http.appartement.response.ApartmentResponse;
import infoco.immo.http.tenant.mapper.TenantMapper;
import infoco.immo.http.tenant.response.TenantResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping( "/api/apartment")
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
    public ResponseEntity<List<ApartmentResponse>> getAll() {
        List<ApartmentResponse> apartmentResponseList = Optional.of(appartmentService.get().stream().map(ApartmentMapper.INSTANCE::domainToResponse).collect(Collectors.toList())).orElse(new ArrayList<>());
        return new ResponseEntity<>(apartmentResponseList, HttpStatus.OK);
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
