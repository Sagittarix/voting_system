package voting.dto.results;

import voting.dto.district.DistrictShortDTO;
import voting.model.County;
import voting.model.District;
import voting.results.model.result.*;

import java.util.List;
import java.util.stream.Collectors;

import static voting.results.model.result.ResultType.*;

/**
 * Created by domas on 2/27/17.
 */
public class DistrictResultSummaryDTO {

    private DistrictShortDTO district;
    private ResultShortDTO result;
    private Long confirmedCountyResults = 0L;
    private int totalCounties = 0;
    private Long voterCount;
    private Long totalBallots = 0L;
    private Long spoiledBallots = 0L;


    public DistrictResultSummaryDTO(District district, ResultType type) {
        this.district = new DistrictShortDTO(district);
        this.voterCount = district.getVoterCount();

        List<County> counties = district.getCounties();
        this.totalCounties = counties.size();


        DistrictResult result = (type == SINGLE_MANDATE) ?
                district.getSmResult() :
                district.getMmResult();
        if (result != null) {
            this.result = new ResultShortDTO(result);
        }

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

    public ResultShortDTO getResult() {
        return result;
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
