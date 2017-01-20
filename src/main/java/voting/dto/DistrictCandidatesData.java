package voting.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DistrictCandidatesData that = (DistrictCandidatesData) o;
        return Objects.equals(districtId, that.districtId) &&
                Objects.equals(candidatesData, that.candidatesData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(districtId, candidatesData);
    }

    @Override
    public String toString() {
        return "DistrictCandidatesData{" +
                "districtId=" + districtId +
                ", candidatesData=" + candidatesData +
                '}';
    }
}
