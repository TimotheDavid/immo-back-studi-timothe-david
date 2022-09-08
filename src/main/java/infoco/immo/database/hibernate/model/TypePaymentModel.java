package infoco.immo.database.hibernate.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;



@Entity
@Table(name = "type_payment")
public class TypePaymentModel {

    @Id
    @GeneratedValue
    private int id;
    private UUID uuid;

    @Column(name = "senspaymentid")
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    private List<SensPaymentModel> types = new ArrayList<>();

    private type_to_pay type;
}

enum type_to_pay{Cheque, Carte, Espece};
