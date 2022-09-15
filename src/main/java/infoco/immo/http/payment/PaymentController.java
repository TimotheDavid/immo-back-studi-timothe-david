package infoco.immo.http.payment;


import infoco.immo.core.Payment;
import infoco.immo.http.payment.dto.CreateDTOPayment;
import infoco.immo.http.payment.dto.UpdateDTOPayment;
import infoco.immo.http.payment.mapper.PaymentMapper;
import infoco.immo.http.payment.response.PaymentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping
    public void create(@RequestBody CreateDTOPayment paymentdto){
        Payment payment = PaymentMapper.INSTANCE.createDTOToDomain(paymentdto);
        paymentService.create(payment);
    }

    @GetMapping
    public ResponseEntity<List<PaymentResponse>> getAll(){
        return new ResponseEntity<>(paymentService.get().stream().map(PaymentMapper.INSTANCE::domainToResponse).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping(value = "/{uuid}")
    public ResponseEntity<PaymentResponse> get(@PathVariable String uuid){
        Payment payment =  paymentService.get(UUID.fromString(uuid));

        return ResponseEntity.ok(PaymentMapper.INSTANCE.domainToResponse(payment));

    }

    @DeleteMapping(value = "/{uuid}")
    public void delete(@PathVariable String uuid){
        paymentService.delete(UUID.fromString(uuid));
    }

    @PutMapping
    public void update(@RequestBody UpdateDTOPayment paymentDTO){
        Payment payment = PaymentMapper.INSTANCE.updatePaymentDTOToDomain(paymentDTO);
        paymentService.update(payment);
    }

}
