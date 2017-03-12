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
    private int confirmedCountyResults = 0;
    private int totalCounties = 0;
    private Long voterCount;
    private Long validBallots = 0L;
    private Long totalBallots = 0L;
    private Long spoiledBallots = 0L;


    public DistrictResultSummaryDTO(DistrictResult result) {
        District district = result.getDistrict();
        this.district = new DistrictShortDTO(district);
        this.voterCount = district.getVoterCount();
        this.totalCounties = result.getTotalCountyResults();

        if (result != null) {
            this.result = new ResultShortDTO(result);
            this.confirmedCountyResults = result.getConfirmedCountyResults();
            this.totalBallots = result.getTotalBallots();
            this.validBallots = result.getValidBallots();
            this.spoiledBallots = result.getSpoiledBallots();
        }
    }


    public DistrictShortDTO getDistrict() {
        return district;
    }

    public ResultShortDTO getResult() {
        return result;
    }

    public int getConfirmedCountyResults() {
        return confirmedCountyResults;
    }

    public int getTotalCounties() {
        return totalCounties;
    }

    public Long getVoterCount() {
        return voterCount;
    }

    public Long getValidBallots() {
        return validBallots;
    }

    public Long getTotalBallots() {
        return totalBallots;
    }

    public Long getSpoiledBallots() {
        return spoiledBallots;
    }
}
