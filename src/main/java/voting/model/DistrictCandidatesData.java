package voting.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by domas on 1/14/17.
 */
public class DistrictCandidatesData {

    private Long districtId;
    @JsonProperty("candidates")
    private List<CandidateData> candidatesData;

}
