package voting.dto.results;

import voting.dto.candidate.CandidateShortDTO;
import voting.model.District;
import voting.results.model.result.DistrictSMResult;
import voting.results.model.votecount.CandidateVote;
import voting.results.model.votecount.Vote;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static voting.results.model.result.ResultType.SINGLE_MANDATE;

/**
 * Created by domas on 3/7/17.
 */
public class DistrictSmResultSummaryDTO extends DistrictResultSummaryDTO{

    private CandidateShortDTO candidate;

    public DistrictSmResultSummaryDTO(District district) {
        super(district, SINGLE_MANDATE);
        DistrictSMResult result = district.getSmResult();
        if (result != null && result.getTotalBallots() > 0) {

            //TODO: TEMP, move sorting to where it belongs
            List<CandidateVote> votes = result.getVotes();

            votes.sort(Collections.reverseOrder(Comparator.comparing(Vote::getVoteCount)));

            CandidateVote top1vote = votes.get(0);

            this.candidate = new CandidateShortDTO(top1vote.getCandidate());
        }
    }

    public CandidateShortDTO getCandidate() {
        return candidate;
    }
}
