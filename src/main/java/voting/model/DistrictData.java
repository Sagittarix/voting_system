package voting.model;

import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by domas on 1/12/17.
 */

public class DistrictData {

    private Long id;
    private String name;
    private List<CountyData> countiesData;
    private List<CandidateData> candidatesData;

    public DistrictData() {
    }

    public DistrictData(Long id, String name, List<CountyData> countiesData, List<CandidateData> candidatesData) {
        this.id = id;
        this.name = name;
        this.countiesData = countiesData;
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

    public List<CountyData> getCountiesData() {
        return countiesData;
    }

    public void setCountiesData(List<CountyData> countiesData) {
        this.countiesData = countiesData;
    }

    public List<CandidateData> getCandidatesData() {
        return candidatesData;
    }

    public void setCandidatesData(List<CandidateData> candidatesData) {
        this.candidatesData = candidatesData;
    }
}
