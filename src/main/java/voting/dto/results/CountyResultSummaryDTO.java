package voting.dto.results;

import voting.dto.county.CountyShortDTO;
import voting.model.County;
import voting.results.model.result.CountyResult;
import voting.results.model.result.ResultType;

import java.util.Date;

/**
 * Created by domas on 3/7/17.
 */
public class CountyResultSummaryDTO {

    private CountyShortDTO county;
    private Date createdOn;
    private Long voterCount;
    private Long totalBallots;
    private Long spoiledBallots;

    public CountyResultSummaryDTO(County county, ResultType type) {
        CountyResult result = (type == ResultType.SINGLE_MANDATE) ?
                county.getSmResult() :
                county.getMmResult();

        this.county = new CountyShortDTO(county);
        this.voterCount = county.getVoterCount();

        if (result != null) {
            this.createdOn = result.getCreatedOn();
            this.totalBallots = result.getTotalBallots();
            this.spoiledBallots = result.getSpoiledBallots();
        }
    }

    public CountyShortDTO getCounty() {
        return county;
    }

    public Date getCreatedOn() {
        return createdOn;
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
