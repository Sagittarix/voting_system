package voting.dto;

import voting.model.County;
import voting.model.CountyRep;

import java.util.Objects;

/**
 * Created by domas on 1/12/17.
 */
public class CountyRepresentativeRepresentation {

    private Long id;
    private String personalId;
    private String firstName;
    private String lastName;
    private String email;
    private CountyRepresentation county;
    private String districtName;

    public CountyRepresentativeRepresentation() { }

    public CountyRepresentativeRepresentation(CountyRep countyRep) {
        this.id = countyRep.getId();
        this.personalId =
        this.firstName = countyRep.getFirstName();
        this.lastName = countyRep.getLastName();
        this.email = countyRep.getEmail();

        County county = countyRep.getCounty();
        this.county = new CountyRepresentation(county);
        this.districtName = county.getDistrict().getName();

    }

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

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public CountyRepresentation getCounty() {
        return county;
    }

    public void setCounty(CountyRepresentation county) {
        this.county = county;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountyRepresentativeRepresentation that = (CountyRepresentativeRepresentation) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(personalId, that.personalId) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(email, that.email) &&
                Objects.equals(county.getId(), that.county.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, personalId, firstName, lastName, email, county.getId());
    }

    @Override
    public String toString() {
        return "CountyRepresentativeRepresentation {" +
                "id=" + id +
                ", personalId='" + personalId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", countyId=" + county.getId() +
                '}';
    }
}
