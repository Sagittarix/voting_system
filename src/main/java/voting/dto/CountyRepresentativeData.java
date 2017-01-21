package voting.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Objects;

/**
 * Created by domas on 1/12/17.
 */
public class CountyRepresentativeData {

    private Long id;
    @NotNull
    @Pattern(regexp = "\\d{11}")
    private String personalId;
    @NotNull
    @Length(min = 1, max = 40)
    private String firstName;
    @NotNull
    @Length(min = 1, max = 40)
    private String lastName;
    @NotNull
    private Long countyId;
    @NotNull
    private String role;
    @NotNull
    private String password_digest;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
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

    public Long getCountyId() {
        return countyId;
    }

    public void setCountyId(Long countyId) {
        this.countyId = countyId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
        CountyRepresentativeData that = (CountyRepresentativeData) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(personalId, that.personalId) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(countyId, that.countyId) &&
                Objects.equals(role, that.role) &&
                Objects.equals(password_digest, that.password_digest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, personalId, firstName, lastName, countyId, role, password_digest);
    }

    @Override
    public String toString() {
        return "CountyRepresentativeData{" +
                "id=" + id +
                ", personalId='" + personalId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", countyId=" + countyId +
                ", role='" + role + '\'' +
                ", password_digest='" + password_digest + '\'' +
                '}';
    }
}
