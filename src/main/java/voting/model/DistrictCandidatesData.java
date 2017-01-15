package voting.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by domas on 1/14/17.
 */
public class DistrictCandidatesData {

    @NotNull
    private Long districtId;
    @NotNull
    @JsonProperty("candidates")
    private List<CandidateData> candidatesData;

}
