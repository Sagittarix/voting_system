package voting.dto.results;

import voting.results.model.result.Result;

/**
 * Created by domas on 2/27/17.
 */
public class ResultRepresentation {

    private Long id;
    private Long spoiledBallots;

    public ResultRepresentation() {
    }

    public ResultRepresentation(Result result) {
        id = result.getId();
        spoiledBallots = result.getSpoiledBallots();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSpoiledBallots() {
        return spoiledBallots;
    }

    public void setSpoiledBallots(Long spoiledBallots) {
        this.spoiledBallots = spoiledBallots;
    }
}
