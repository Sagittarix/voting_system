package voting.dto.results;

import voting.dto.county.CountyShortDTO;
import voting.model.County;
import voting.results.model.result.CountyResult;
import voting.results.model.result.ResultType;
import voting.utils.Constants;

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
    private String link;

    public CountyResultSummaryDTO(County county, ResultType type) {
        CountyResult result = (type == ResultType.SINGLE_MANDATE) ?
                county.getSmResult() :
                county.getMmResult();

        this.county = new CountyShortDTO(county);
        this.voterCount = county.getVoterCount();

        if (result != null && result.isConfirmed()) {
            this.createdOn = result.getCreatedOn();
            this.totalBallots = result.getTotalBallots();
            this.spoiledBallots = result.getSpoiledBallots();
        }

        this.link = constructLink(county, type);
    }

    private String constructLink(County county, ResultType type) {
        String link = String.format("%s/results/county/%d", Constants.API_ROOT_URL, county.getId());
        if (type == ResultType.SINGLE_MANDATE) {
            link.concat("/single-mandate");
        } else {
            link.concat("/multi-mandate");
        }
        return link;
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

    public String getLink() {
        return link;
    }
}
