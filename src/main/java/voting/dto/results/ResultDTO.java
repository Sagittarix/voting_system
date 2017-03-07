package voting.dto.results;

import voting.results.model.result.Result;

/**
 * Created by domas on 2/27/17.
 */
public class ResultDTO {

    private Long id;
    private Long spoiledBallots;
    private Long totalBallots;
    private Long voterCount;

    public ResultDTO(Result result) {
        this.id = result.getId();
        this.spoiledBallots = result.getSpoiledBallots();
        this.totalBallots = result.getTotalBallots();
    }

    public Long getId() {
        return id;
    }

    public Long getSpoiledBallots() {
        return spoiledBallots;
    }

    public Long getTotalBallots() {
        return totalBallots;
    }

    public Long getVoterCount() {
        return voterCount;
    }

    public void setVoterCount(Long voterCount) {
        this.voterCount = voterCount;
    }
}
