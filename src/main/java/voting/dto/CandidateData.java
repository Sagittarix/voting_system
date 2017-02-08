package voting.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Objects;

/**
 * Created by domas on 1/12/17.
 */
public class CandidateData {

    private Long id;

    @NotNull(message = "AK negali būti tuščias")
    @Pattern(regexp = "\\d{11}", message = "Spring - Asmens kode vien skaitmenys")
    private String personId;

    @NotNull(message = "Vardas negali būti tuščias")
    @Length(min = 3, max = 40, message = "Spring - Vardo ilgis nuo 3 iki 40 simbolių")
    private String firstName;

    @NotNull(message = "Pavardė negali būti tuščia")
    @Length(min = 3, max = 40, message = "Spring - Pavardės ilgis nuo 3 iki 40 simbolių")
    private String lastName;

    // not sure kuriu reikia, kolkas palieku visus
    private Long districtId;
    private String districtName;
    private Long partyId;
    private String partyName;
    private Long positionInPartyList;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
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

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public Long getPartyId() {
        return partyId;
    }

    public void setPartyId(Long partyId) {
        this.partyId = partyId;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public Long getPositionInPartyList() {
        return positionInPartyList;
    }

    public void setPositionInPartyList(Long positionInPartyList) {
        this.positionInPartyList = positionInPartyList;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CandidateData that = (CandidateData) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(personId, that.personId) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(districtId, that.districtId) &&
                Objects.equals(districtName, that.districtName) &&
                Objects.equals(partyId, that.partyId) &&
                Objects.equals(partyName, that.partyName) &&
                Objects.equals(positionInPartyList, that.positionInPartyList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, personId, firstName, lastName, districtId, districtName, partyId, partyName, positionInPartyList);
    }

    @Override
    public String toString() {
        return "CandidateData{" +
                "id=" + id +
                ", personId='" + personId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", districtId=" + districtId +
                ", districtName='" + districtName + '\'' +
                ", partyId=" + partyId +
                ", partyName='" + partyName + '\'' +
                ", positionInPartyList=" + positionInPartyList +
                '}';
    }
}
