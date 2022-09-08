package infoco.immo.database.hibernate.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "civility")
public class CivilityModel {
    @Id
    @GeneratedValue
    private int id;

    private UUID uuid;
    private String civility;
}
