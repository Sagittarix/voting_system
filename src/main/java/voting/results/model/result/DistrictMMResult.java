package voting.results.model.result;


import voting.model.District;
import voting.results.model.votecount.PartyVote;

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

    public DistrictMMResult() {
    }

    public DistrictMMResult(District district) {
        super(district);
    }

    public List<PartyVote> getVotes() {
        return super.getUnitVotes().stream().map(vc -> (PartyVote) vc).collect(Collectors.toList());
    }

}
