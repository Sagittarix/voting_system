package voting.results.model.result;


import voting.results.model.votecount.PartyVoteCount;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by domas on 2/22/17.
 */
@Entity
@DiscriminatorValue(value = "district_mm")
public class DistrictMMResult extends DistrictResult {

    public List<PartyVoteCount> getVotes() {
        return super.getVoteCounts().stream().map(vc -> (PartyVoteCount) vc).collect(Collectors.toList());
    }

}
