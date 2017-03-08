package voting.dto.results;

import voting.dto.district.DistrictShortDTO;
import voting.model.District;
import voting.results.model.result.DistrictResult;
import voting.results.model.result.DistrictSMResult;
import voting.results.model.result.ResultType;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by domas on 2/27/17.
 */
public class DistrictResultDTO extends ResultDTO {

    private DistrictShortDTO district;
    private Long confirmedCountyResults;
    private int totalCounties;
    private List<CountyResultSummaryDTO> countyResults;


    public DistrictResultDTO(DistrictResult result) {
        super(result);
        District district = result.getDistrict();
        this.district = district == null ? null : new DistrictShortDTO(district);
        this.setVoterCount(district.getVoterCount());

        ResultType type;
        if (result instanceof DistrictSMResult) {
            type = ResultType.SINGLE_MANDATE;
        } else {
            type = ResultType.MULTI_MANDATE;
        }

        this.countyResults = district.getCounties().stream()
                .map(c -> new CountyResultSummaryDTO(c, type))
                .collect(Collectors.toList());

        this.confirmedCountyResults = countyResults.stream().filter(r -> r.getCreatedOn() != null).count();
        this.totalCounties = countyResults.size();
    }

    public DistrictShortDTO getDistrict() {
        return district;
    }

    public Long getConfirmedCountyResults() {
        return confirmedCountyResults;
    }

    public int getTotalCounties() {
        return totalCounties;
    }

    public List<CountyResultSummaryDTO> getCountyResults() {
        return countyResults;
    }
}
