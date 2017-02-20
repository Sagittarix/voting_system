package voting.model;

import javax.persistence.*;
import java.util.Objects;

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
    private String email;
    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = {}
    )
    @JoinColumn(name = "county_id", nullable = false)
    private County county;

    public CountyRep() {
    }

    public CountyRep(String firstName, String lastName, String email, County county) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
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

    public String getEmail() { return email; }

    public County getCounty() {
        return county;
    }

    public void setCounty(County county) {
        this.county = county;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountyRep countyRep = (CountyRep) o;
        return Objects.equals(id, countyRep.id) &&
                Objects.equals(firstName, countyRep.firstName) &&
                Objects.equals(lastName, countyRep.lastName) &&
                Objects.equals(email, countyRep.email) &&
                Objects.equals(county, countyRep.county);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, county);
    }

    @Override
    public String toString() {
        return "CountyRep{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", county=" + county +
                '}';
    }
}
