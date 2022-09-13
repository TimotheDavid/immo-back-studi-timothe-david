package infoco.immo.core;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRent {

    private UUID id;
    private UUID payment;
    private UUID rent;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getRent() {
        return rent;
    }

    public void setRent(UUID rent) {
        this.rent = rent;
    }

;

    public UUID getPayment() {
        return payment;
    }

    public void setPayment(UUID payment) {
        this.payment = payment;
    }


}
