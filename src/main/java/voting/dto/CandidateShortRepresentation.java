package voting.dto;

import voting.model.Candidate;

/**
 * Created by domas on 2/27/17.
 */
public class CandidateShortRepresentation {

    private Long id;
    private String firstName;
    private String lastName;
    private PartyShortRepresentation party;
    private DistrictShortRepresentation district;

    public CandidateShortRepresentation() {
    }

    public CandidateShortRepresentation(Candidate candidate) {
        id = candidate.getId();
        firstName = candidate.getFirstName();
        lastName = candidate.getLastName();
        party = candidate.getParty() == null ? null : new PartyShortRepresentation(candidate.getParty());
        district = candidate.getDistrict() == null ? null : new DistrictShortRepresentation(candidate.getDistrict());
    }

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

    public PartyShortRepresentation getParty() {
        return party;
    }

    public void setParty(PartyShortRepresentation party) {
        this.party = party;
    }

    public DistrictShortRepresentation getDistrict() {
        return district;
    }

    public void setDistrict(DistrictShortRepresentation district) {
        this.district = district;
    }
}
