package voting.dto.results.unused;

import voting.dto.district.DistrictShortDTO;
import voting.dto.party.PartyShortDTO;
import voting.dto.results.ResultShortDTO;
import voting.model.District;
import voting.results.model.result.DistrictMMResult;
import voting.results.model.votecount.PartyVote;
import voting.results.model.votecount.Vote;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by domas on 3/7/17.
 */
public class DistrictMmResultSummaryDTO {

    private DistrictShortDTO district;
    private ResultShortDTO result;
    private PartyShortDTO party;
    private Long voteCount;
    private Long validBallots;
    private Long totalBallots;

    public DistrictMmResultSummaryDTO(District district) {
        this.district = new DistrictShortDTO(district);
        DistrictMMResult result = district.getMmResult();
        if (result != null && result.getTotalBallots() > 0) {
            this.result = new ResultShortDTO(result);

            List<PartyVote> votes = result.getVotes();
            votes.sort(Collections.reverseOrder(Comparator.comparing(Vote::getVoteCount)));
            PartyVote top1vote = votes.get(0);

            this.party= new PartyShortDTO(top1vote.getParty());
            this.voteCount = top1vote.getVoteCount();
            this.validBallots = result.getValidBallots();
            this.totalBallots = result.getTotalBallots();
        }
    }

    public DistrictShortDTO getDistrict() {
        return district;
    }

    public ResultShortDTO getResult() {
        return result;
    }

    public PartyShortDTO getParty() {
        return party;
    }

    public Long getVoteCount() {
        return voteCount;
    }

    public Long getValidBallots() {
        return validBallots;
    }

    public Long getTotalBallots() {
        return totalBallots;
    }

}
