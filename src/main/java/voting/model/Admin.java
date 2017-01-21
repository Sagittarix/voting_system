package voting.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

/**
 * Created by domas on 1/21/17.
 */
@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String personalId;
    private String firstName;
    private String lastName;
    private String role;
    private String password_digest;

    public Admin(String personalId, String firstName, String lastName, String role, String password_digest) {
        this.personalId = personalId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.password_digest = password_digest;
    }

    public Long getId() {
        return id;
    }

    public String getPersonalId() {
        return personalId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getRole() {
        return role;
    }

    public String getPassword_digest() {
        return password_digest;
    }

    public void setPassword_digest(String password_digest) {
        this.password_digest = password_digest;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
        return Objects.equals(id, admin.id) &&
                Objects.equals(personalId, admin.personalId) &&
                Objects.equals(firstName, admin.firstName) &&
                Objects.equals(lastName, admin.lastName) &&
                Objects.equals(role, admin.role) &&
                Objects.equals(password_digest, admin.password_digest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, personalId, firstName, lastName, role, password_digest);
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", personalId='" + personalId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role='" + role + '\'' +
                ", password_digest='" + password_digest + '\'' +
                '}';
    }
}
