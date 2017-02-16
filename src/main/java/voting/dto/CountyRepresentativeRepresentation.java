package voting.dto;

import voting.factory.RepresentationFactory;
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
    private CountyRepresentation county;


    public CountyRepresentativeRepresentation() { }

    public CountyRepresentativeRepresentation(CountyRep countyRep) {
        this.id = countyRep.getId();
        this.personalId =
        this.firstName = countyRep.getFirstName();
        this.lastName = countyRep.getLastName();
        this.county = new CountyRepresentation(countyRep.getCounty());
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

    public CountyRepresentation getCounty() {
        return county;
    }

    public void setCounty(CountyRepresentation county) {
        this.county = county;
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
                Objects.equals(county.getId(), that.county.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, personalId, firstName, lastName, county.getId());
    }

    @Override
    public String toString() {
        return "CountyRepresentativeRepresentation{" +
                "id=" + id +
                ", personalId='" + personalId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", countyId=" + county.getId() +
                '}';
    }
}
