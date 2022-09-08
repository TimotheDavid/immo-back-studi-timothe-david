package infoco.immo.database.hibernate.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ORIGIN")
public class OriginModel {


    @Id
    @GeneratedValue
    private int id;
    private String origin;
}
