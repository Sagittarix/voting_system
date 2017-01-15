package voting.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by domas on 1/14/17.
 */
public class DistrictCandidatesData {

    @NotNull
    private Long districtId;
    @Valid
    @NotNull
    @JsonProperty("candidates")
    private List<CandidateData> candidatesData;


    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public List<CandidateData> getCandidatesData() {
        return candidatesData;
    }

    public void setCandidatesData(List<CandidateData> candidatesData) {
        this.candidatesData = candidatesData;
    }
}
