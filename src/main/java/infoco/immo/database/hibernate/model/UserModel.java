package infoco.immo.database.hibernate.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
public class UserModel {

    @Id
    @GeneratedValue
    private int id;

    private UUID uuid;
    private String name;
    private String email;
    private String password;

    @OneToMany
    private List<AuthenticationModel> authentication = new ArrayList<>();

}
