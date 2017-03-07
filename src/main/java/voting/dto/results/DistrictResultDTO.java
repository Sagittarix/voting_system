package voting.dto.results;

import voting.dto.district.DistrictShortDTO;
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
    private List<CountyResultSummaryDTO> countyResults;


    public DistrictResultDTO(DistrictResult result) {
        super(result);
        this.district = result.getDistrict() == null ? null : new DistrictShortDTO(result.getDistrict());
        this.setVoterCount(result.getDistrict().getVoterCount());

        ResultType type;
        if (result instanceof DistrictSMResult) {
            type = ResultType.SINGLE_MANDATE;
        } else {
            type = ResultType.MULTI_MANDATE;
        }

        this.countyResults = result.getDistrict().getCounties().stream()
                .map(c -> new CountyResultSummaryDTO(c, type))
                .collect(Collectors.toList());
    }

    public DistrictShortDTO getDistrict() {
        return district;
    }

    public List<CountyResultSummaryDTO> getCountyResults() {
        return countyResults;
    }
}
