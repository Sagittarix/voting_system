package voting.model;

import javax.persistence.*;

/**
 * Created by domas on 1/10/17.
 */
@Entity
public class CountyRep {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    @OneToOne
    private County county;

    public CountyRep() {
    }

    public CountyRep(String firstName, String lastName, County county) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.county = county;
    }

    public Long getId() {

        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public County getCounty() {
        return county;
    }

    public void setCounty(County county) {
        this.county = county;
    }
}
