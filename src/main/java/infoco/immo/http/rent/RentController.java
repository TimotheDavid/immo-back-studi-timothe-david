package infoco.immo.http.rent;

import infoco.immo.core.Rent;
import infoco.immo.core.RentTenant;
import infoco.immo.http.rent.dto.CreateRentDTO;
import infoco.immo.http.rent.dto.UpdateRentDTO;
import infoco.immo.http.rent.mapper.RentMappers;
import infoco.immo.http.rent.response.RentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
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

    @GetMapping(value = "/tenant")
    public  ResponseEntity<List<RentTenant>> getRentTenant(){
        return ResponseEntity.ok(rentService.getAllRentTenant());
    }

    @GetMapping(value = "/receipt")
    @ResponseBody
    public ResponseEntity getReceipt(@RequestParam(required = true) String  rent) throws IOException {
        InputStream inputStream = rentService.generateRentReceipt("", "", rent);

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
