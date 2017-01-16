package voting.model;

import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Created by domas on 1/12/17.
 */
public class CountyRepData {

    private Long id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private Long countyId;


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

    public Long getCountyId() {
        return countyId;
    }

    public void setCountyId(Long countyId) {
        this.countyId = countyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountyRepData that = (CountyRepData) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(countyId, that.countyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, countyId);
    }

    @Override
    public String toString() {
        return "CountyRepData{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", countyId=" + countyId +
                '}';
    }
}
