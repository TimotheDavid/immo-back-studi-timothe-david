package infoco.immo.core;

import lombok.*;

import java.util.UUID;


@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    private Float amount;
    private String datePayment;
    private Float landlorPart;
    private Float agencyPart;

    private UUID rentId;
    private UUID id;
    private TypePayment typePayment;
    private Origin origin;
    private Boolean sens;
    private UUID paymentRentId;

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getDatePayment() {
        return datePayment;
    }

    public void setDatePayment(String datePayment) {
        this.datePayment = datePayment;
    }

    public Float getLandlorPart() {
        return landlorPart;
    }

    public void setLandlorPart(Float landlorPart) {
        this.landlorPart = landlorPart;
    }

    public Float getAgencyPart() {
        return agencyPart;
    }

    public void setAgencyPart(Float agencyPart) {
        this.agencyPart = agencyPart;
    }

    public UUID getRentId() {
        return rentId;
    }

    public void setRentId(UUID rentId) {
        this.rentId = rentId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public TypePayment getTypePayment() {
        return typePayment;
    }

    public void setTypePayment(TypePayment typePayment) {
        this.typePayment = typePayment;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public Boolean getSens() {
        return sens;
    }

    public void setSens(Boolean sens) {
        this.sens = sens;
    }

    public UUID getPaymentRentId() {
        return paymentRentId;
    }

    public void setPaymentRentId(UUID paymentRentId) {
        this.paymentRentId = paymentRentId;
    }


}


