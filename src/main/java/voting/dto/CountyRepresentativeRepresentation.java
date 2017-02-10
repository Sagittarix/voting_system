package voting.dto;

import voting.model.CountyRepresentative;

import java.util.Objects;

/**
 * Created by domas on 1/12/17.
 */
public class CountyRepresentativeRepresentation {

    private Long id;
    private String personalId;
    private String firstName;
    private String lastName;
    private Long countyId;


    public CountyRepresentativeRepresentation() { }

    public CountyRepresentativeRepresentation(CountyRepresentative countyRep) {
        this.id = countyRep.getId();
        this.personalId = countyRep.getPersonalId();
        this.firstName = countyRep.getFirstName();
        this.lastName = countyRep.getLastName();
        this.countyId = countyRep.getCounty().getId();
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
        CountyRepresentativeRepresentation that = (CountyRepresentativeRepresentation) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(personalId, that.personalId) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(countyId, that.countyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, personalId, firstName, lastName, countyId);
    }

    @Override
    public String toString() {
        return "CountyRepresentativeRepresentation{" +
                "id=" + id +
                ", personalId='" + personalId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", countyId=" + countyId +
                '}';
    }
}
