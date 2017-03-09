package voting.dto.results;

import voting.dto.district.DistrictShortDTO;
import voting.model.County;
import voting.model.District;
import voting.results.model.result.CountyResult;
import voting.results.model.result.DistrictResult;
import voting.results.model.result.DistrictSMResult;
import voting.results.model.result.ResultType;
import voting.utils.Constants;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by domas on 2/27/17.
 */
public class DistrictResultSummaryDTO {

    private DistrictShortDTO district;
    private DistrictResultShortDTO result;
    private Long confirmedCountyResults = 0L;
    private int totalCounties;
    private Long voterCount;
    private Long totalBallots;
    private Long spoiledBallots;


    public DistrictResultSummaryDTO(DistrictResult result) {
        District district = result.getDistrict();
        this.district = new DistrictShortDTO(district);
        this.result = new DistrictResultShortDTO(result);
        this.voterCount = district.getVoterCount();

        List<County> counties = district.getCounties();
        this.totalCounties = counties.size();

        ResultType type = result instanceof DistrictSMResult ?
                ResultType.SINGLE_MANDATE :
                ResultType.MULTI_MANDATE;

        List<CountyResult> countyResults = district.getCounties().stream()
                .map(c -> c.getResultByType(type))
                .collect(Collectors.toList());

        countyResults.stream()
                .filter(r -> r != null && r.isConfirmed())
                .forEach(r -> {
                    confirmedCountyResults++;
                    totalBallots += r.getTotalBallots();
                    spoiledBallots += r.getSpoiledBallots();
                });
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

    public Long getVoterCount() {
        return voterCount;
    }

    public Long getTotalBallots() {
        return totalBallots;
    }

    public Long getSpoiledBallots() {
        return spoiledBallots;
    }

}
