package infoco.immo.database.hibernate.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "APARTMENT")
public class ApartmentModel {

    @Id
    @GeneratedValue
    private int id;

    private UUID uuid;
    private String address;
    private String complement;
    private String city;

    @Column(name = "postal_code")
    private String postalCode;
    private Float charge;
    private Float rent;
    private Float deposit;
}
