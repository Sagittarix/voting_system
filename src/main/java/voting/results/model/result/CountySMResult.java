package voting.results.model.result;


import voting.results.model.votecount.CandidateVoteCount;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by domas on 2/22/17.
 */
@Entity
@DiscriminatorValue(value = "county_sm")
public class CountySMResult extends CountyResult {

    public List<CandidateVoteCount> getVotes() {
        return super.getVoteCounts().stream().map(vc -> (CandidateVoteCount) vc).collect(Collectors.toList());
    }
}
