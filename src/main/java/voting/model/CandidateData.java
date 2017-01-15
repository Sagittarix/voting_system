package voting.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by domas on 1/12/17.
 */
public class CandidateData {

    private Long id;
    @NotNull
    @Pattern(regexp = "\\d{11}")
    private String personId;
    @NotNull
    @Length(min = 1, max = 40)
    private String firstName;
    @NotNull
    @Length(min = 1, max = 40)
    private String lastName;


    // not sure ar situ reik, kolkas palieku
    private Long partyId;
    private String partyShortName;
    private Long numberInPartyList;


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

    public String getPartyShortName() {
        return partyShortName;
    }

    public void setPartyShortName(String partyShortName) {
        this.partyShortName = partyShortName;
    }

    public Long getNumberInPartyList() {
        return numberInPartyList;
    }

    public void setNumberInPartyList(Long numberInPartyList) {
        this.numberInPartyList = numberInPartyList;
    }
}
