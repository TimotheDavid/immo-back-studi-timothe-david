package infoco.immo.http.payment;


import infoco.immo.core.Payment;
import infoco.immo.http.payment.dto.CreateDTOPayment;
import infoco.immo.http.payment.dto.UpdateDTOPayment;
import infoco.immo.http.payment.mapper.PaymentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    public List<Payment> getAll(){
        return paymentService.get();
    }

    @GetMapping(value = "/{uuid}")
    public Payment get(@PathVariable String uuid){
        return paymentService.get(UUID.fromString(uuid));
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
