package voting.dto;

import org.hibernate.validator.constraints.Length;
import voting.model.Candidate;
import voting.model.Party;

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

    // not sure ar situ reik, kolkas palieku
    private Long partyId;
    private String partyName;
    private String partyShortName;
    private Long positionInPartyList;


    public CandidateRepresentation() {

    }

    public CandidateRepresentation(Candidate candidate) {
        this.id = candidate.getId();
        this.personId = candidate.getPersonId();
        this.firstName = candidate.getFirstName();
        this.lastName = candidate.getLastName();
        Party party = candidate.getParty();
        if (party != null) {
            this.partyId = party.getId();
            this.partyName = party.getName();
            this.partyShortName = party.getShortName();
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

    public String getPartyShortName() {
        return partyShortName;
    }

    public void setPartyShortName(String partyShortName) {
        this.partyShortName = partyShortName;
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
        CandidateRepresentation that = (CandidateRepresentation) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(personId, that.personId) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(partyId, that.partyId) &&
                Objects.equals(partyName, that.partyName) &&
                Objects.equals(partyShortName, that.partyShortName) &&
                Objects.equals(positionInPartyList, that.positionInPartyList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, personId, firstName, lastName, partyId, partyName, partyShortName, positionInPartyList);
    }

    @Override
    public String toString() {
        return "CandidateRepresentation{" +
                "id=" + id +
                ", personId='" + personId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", partyId=" + partyId +
                ", partyName='" + partyName + '\'' +
                ", partyShortName='" + partyShortName + '\'' +
                ", positionInPartyList=" + positionInPartyList +
                '}';
    }
}
