package voting.dto.results;

import voting.results.model.result.CountyMMResult;
import voting.results.model.result.CountySMResult;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by domas on 2/27/17.
 */
public class CountyMMResultRepresentation extends CountyResultRepresentation {

    private List<PartyVoteRepresentation> votes;

    public CountyMMResultRepresentation() {
    }

    public CountyMMResultRepresentation(CountyMMResult result) {
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
