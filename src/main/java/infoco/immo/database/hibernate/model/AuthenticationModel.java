package infoco.immo.database.hibernate.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "AUTHENTICATION")
public class AuthenticationModel {

    @Id
    @GeneratedValue
    private int id;

    private UUID uuid;
    private String token;
    private String hash;
    private Instant expires;
}


