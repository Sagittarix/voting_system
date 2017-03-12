package voting.results.model.result;


import voting.model.District;
import voting.results.model.votecount.CandidateVote;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by domas on 2/22/17.
 */
@Entity
@DiscriminatorValue(value = "district_sm")
public class DistrictSMResult extends DistrictResult {

    public DistrictSMResult() {
    }

    public DistrictSMResult(District district) {
        super(district);
    }

    public List<CandidateVote> getVotes() {
        return super.getUnitVotes().stream().map(vc -> (CandidateVote) vc).collect(Collectors.toList());
    }

}
