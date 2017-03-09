package voting.dto.results;

import voting.dto.district.DistrictShortDTO;
import voting.dto.party.PartyShortDTO;
import voting.model.District;
import voting.results.model.result.DistrictMMResult;
import voting.results.model.result.ResultType;
import voting.results.model.votecount.PartyVote;
import voting.results.model.votecount.Vote;
import voting.utils.Constants;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by domas on 3/7/17.
 */
public class DistrictMmResultSummaryDTO {

    private DistrictShortDTO district;
    private DistrictResultShortDTO result;
    private PartyShortDTO party;
    private Long voteCount;
    private Long totalBallots;

    public DistrictMmResultSummaryDTO(District district) {
        this.district = new DistrictShortDTO(district);
        DistrictMMResult result = district.getMmResult();
        if (result != null && result.getTotalBallots() > 0) {
            this.result = new DistrictResultShortDTO(result);

            List<PartyVote> votes = result.getVotes();
            votes.sort(Collections.reverseOrder(Comparator.comparing(Vote::getVoteCount)));
            PartyVote top1vote = votes.get(0);

            this.party= new PartyShortDTO(top1vote.getParty());
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

    public PartyShortDTO getParty() {
        return party;
    }

    public Long getVoteCount() {
        return voteCount;
    }

    public Long getTotalBallots() {
        return totalBallots;
    }

}
