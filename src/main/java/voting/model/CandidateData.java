package voting.model;

import javax.persistence.ManyToOne;

/**
 * Created by domas on 1/12/17.
 */
public class CandidateData {

    private Long id;
    private String personId;
    private String firstName;
    private String lastName;
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
