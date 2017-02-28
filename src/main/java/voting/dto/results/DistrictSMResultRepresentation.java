package voting.dto.results;

import voting.results.model.result.DistrictSMResult;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by domas on 2/27/17.
 */
public class DistrictSMResultRepresentation extends DistrictResultRepresentation {

    private List<CandidateVoteRepresentation> votes;

    public DistrictSMResultRepresentation() {
    }

    public DistrictSMResultRepresentation(DistrictSMResult result) {
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
