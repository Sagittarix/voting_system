package voting.dto.results;

import voting.results.model.result.DistrictMMResult;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by domas on 2/27/17.
 */
public class DistrictMMResultRepresentation extends DistrictResultRepresentation {

    private List<PartyVoteRepresentation> votes;

    public DistrictMMResultRepresentation() {
    }

    public DistrictMMResultRepresentation(DistrictMMResult result) {
        super(result);
        votes = result.getVotes().stream().map(PartyVoteRepresentation::new).collect(Collectors.toList());
    }

    public List<PartyVoteRepresentation> getVotes() {
        return votes;
    }

    public void setVotes(List<PartyVoteRepresentation> votes) {
        this.votes = votes;
    }
}
