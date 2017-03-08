package voting.dto.results;

import voting.dto.candidate.CandidateShortDTO;
import voting.dto.district.DistrictShortDTO;
import voting.model.District;
import voting.results.model.result.DistrictSMResult;
import voting.results.model.votecount.CandidateVote;
import voting.results.model.votecount.Vote;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by domas on 3/7/17.
 */
public class SingleMandateSummaryDTO {

    private DistrictShortDTO district;
    private DistrictResultShortDTO result;
    private CandidateShortDTO candidate;
    private Long voteCount;
    private Long totalBallots;

    public SingleMandateSummaryDTO(District district) {
        this.district = new DistrictShortDTO(district);
        DistrictSMResult result = district.getSmResult();
        if (result != null && result.getTotalBallots() > 0) {
            this.result = new DistrictResultShortDTO(result);

//            CandidateVote top1vote = result.getVotes().get(0);
            List<CandidateVote> votes = result.getVotes();
            votes.sort(Collections.reverseOrder(Comparator.comparing(Vote::getVoteCount)));
            CandidateVote top1vote = votes.get(0);

            this.candidate = new CandidateShortDTO(top1vote.getCandidate());
            this.voteCount = top1vote.getVoteCount();
            this.totalBallots = result.getTotalBallots();
        }
    }

    public DistrictShortDTO getDistrict() {
        return district;
    }

    public DistrictResultShortDTO getResult() {
        return result;
    }

    public CandidateShortDTO getCandidate() {
        return candidate;
    }

    public Long getVoteCount() {
        return voteCount;
    }

    public Long getTotalBallots() {
        return totalBallots;
    }
}
