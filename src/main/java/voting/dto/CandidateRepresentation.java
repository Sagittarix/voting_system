package voting.dto;

import org.hibernate.validator.constraints.Length;
import voting.model.Candidate;
import voting.model.District;
import voting.model.Party;
import voting.utils.DateUtils;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Objects;

/**
 * Created by domas on 1/12/17.
 */
public class CandidateRepresentation {

    private Long id;
    private String personId;
    private String firstName;
    private String lastName;
    private String description;
    private String birthDate;

    private Long districtId;
    private String districtName;
    private Long partyId;
    private String partyName;
    private Long positionInPartyList;

    public CandidateRepresentation() {
    }

    public CandidateRepresentation(Candidate candidate) {
        this.id = candidate.getId();
        this.personId = candidate.getPersonId();
        this.firstName = candidate.getFirstName();
        this.lastName = candidate.getLastName();
        this.description = candidate.getDescription();
        this.birthDate = DateUtils.stringifyCalendar(candidate.getBirthDate(), "-");
        District district = candidate.getDistrict();
        if (district != null) {
            this.districtId = district.getId();
            this.districtName = district.getName();
        }
        Party party = candidate.getParty();
        if (party != null) {
            this.partyId = party.getId();
            this.partyName = party.getName();
        }
        this.positionInPartyList = candidate.getPositionInPartyList() != null ? candidate.getPositionInPartyList() : 0;
    }


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CandidateRepresentation that = (CandidateRepresentation) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(personId, that.personId) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(description, that.description) &&
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
        return "CandidateRepresentation{" +
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
