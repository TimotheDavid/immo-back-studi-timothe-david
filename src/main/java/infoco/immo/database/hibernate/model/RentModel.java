package infoco.immo.database.hibernate.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name ="rent")
public class RentModel {

    @Id
    @GeneratedValue
    private int id;

    private UUID uuid;
    private Double rent;
    @Column(name = "in_date")
    private  String inDate;
    @Column(name = "in_description")
    private String inDescription;
    @Column(name="out_date")
    private String outDate;
    @Column(name="out_description")
    private String outDescription;
    private Double deposit;
    @Column(name = "agency_pourcent")
    private Double agencyPourcent;

    @Column(name = "apartmentid")
    @ManyToMany
    private List<ApartmentModel> apartments = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "rentModel")
    private List<TenantModel> tenants = new ArrayList<>();

    public List<TenantModel> getTenants() {
        return tenants;
    }

}
