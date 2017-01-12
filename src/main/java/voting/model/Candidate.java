package voting.model;

import javax.persistence.*;

/**
 * Created by domas on 1/10/17.
 */
@Entity
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String personId;
    private String firstName;
    private String lastName;
    @ManyToOne
    private District district;
    @ManyToOne
    private Party party;

    public Candidate() {
    }

    public Candidate(String personId, String firstName, String lastName, District district, Party party) {
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.district = district;
        this.party = party;
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

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }

    @Override
    public String toString() {
        return String.format("%s: %s %s", personId, firstName, lastName);
    }
}
