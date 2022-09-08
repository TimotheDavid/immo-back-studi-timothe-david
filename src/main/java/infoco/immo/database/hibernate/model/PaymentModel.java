package infoco.immo.database.hibernate.model;


import infoco.immo.core.Rent;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "PAYMENT")
public class PaymentModel {


    @Id
    @GeneratedValue
    private int  id;

    private UUID uuid;
    private Float amount;
    @Column(name = "date_payment")
    private Instant datePayment;
    @Column(name = "landlor_part")
    private Float landlorPart;
    @Column(name = "agency_part")
    private Float agencyPart;

    @Column(name = "originid")
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    private List<OriginModel> origins = new ArrayList<>();


    @Column(name = "typepaymentid")
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    private List<TypePaymentModel> types = new ArrayList<>();

    @Column(name = "paymentrentid")
    @ManyToMany()
    private List<RentModel> rents = new ArrayList<>();


}
