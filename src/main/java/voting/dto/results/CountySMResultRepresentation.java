package voting.dto.results;

import voting.results.model.result.CountySMResult;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by domas on 2/27/17.
 */
public class CountySMResultRepresentation extends CountyResultRepresentation {

    private List<CandidateVoteRepresentation> votes;

    public CountySMResultRepresentation() {
    }

    public CountySMResultRepresentation(CountySMResult result) {
        super(result);
        votes = result.getVotes().stream().map(CandidateVoteRepresentation::new).collect(Collectors.toList());
    }

    public List<CandidateVoteRepresentation> getVotes() {
        return votes;
    }

    public void setVotes(List<CandidateVoteRepresentation> votes) {
        this.votes = votes;
    }
}
