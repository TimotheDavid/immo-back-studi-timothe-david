package infoco.immo.database.hibernate.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tenant")
public class TenantModel {

    @Id
    @GeneratedValue
    private int id;

    private UUID uuid;
    @Column(name = "firstname")
    private String firstName;

    private String name;
    private String birthdate;
    private String birthplace;
    private String email;
    @Column(name = "second_email")
    private String secondEmail;
    private String phone;

    @OneToMany
    private List<CivilityModel> civility = new ArrayList<>();
    @ManyToOne
    private RentModel rentModel;


}