package voting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.crypto.bcrypt.BCrypt;

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
    private String password_digest;

    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = {}
    )
    @JoinColumn(name = "county_id", nullable = false)
    private County county;

    public CountyRep() {
    }

    public CountyRep(String firstName, String lastName, String email, String password, County county) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password_digest = password;
        this.county = county;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public County getCounty() {
        return county;
    }

    public void setCounty(County county) {
        this.county = county;
    }

    @JsonIgnore
    public String getPassword_digest() {
        return password_digest;
    }

    public void setPassword_digest(String password_digest) {
        this.password_digest = BCrypt.hashpw(password_digest, BCrypt.gensalt());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountyRep countyRep = (CountyRep) o;
        return Objects.equals(id, countyRep.id) &&
                Objects.equals(firstName, countyRep.firstName) &&
                Objects.equals(lastName, countyRep.lastName) &&
                Objects.equals(email, countyRep.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email);
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
