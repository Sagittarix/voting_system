package voting.model;

import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by domas on 1/12/17.
 */
public class PartyData {

    private Long id;
    private String name;
    private String shortName;
    private List<CandidateData> candidatesData;

    public PartyData() {
    }

    public PartyData(Long id, String name, String shortName, List<CandidateData> candidatesData) {

        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.candidatesData = candidatesData;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public List<CandidateData> getCandidatesData() {
        return candidatesData;
    }

    public void setCandidatesData(List<CandidateData> candidatesData) {
        this.candidatesData = candidatesData;
    }
}
