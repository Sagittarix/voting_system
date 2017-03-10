package voting.results.model.result;

import voting.model.Party;
import voting.results.model.votecount.PartyVote;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by domas on 3/9/17.
 */
public class MultiMandateResultSummary {

    Map<Party, Long> partyVotecount;
    Map<Party, Long> partyMandates;
    Long totalBallots = 0L;
    Long spoiledBallots = 0L;
    List<DistrictMMResult> results;


    public MultiMandateResultSummary(List<Party> parties, List<DistrictMMResult> results) {
        this.totalBallots = 0L;
        this.spoiledBallots = 0L;
        this.partyVotecount = parties.stream().collect(Collectors.toMap(p -> p, p -> 0L));
        this.partyMandates = parties.stream().collect(Collectors.toMap(p -> p, p -> 0L));
        this.results = results;
        this.results.stream().forEach(r -> combineResults(r));
    }


    public void combineResults(Result result) {
        totalBallots += result.getTotalBallots();
        spoiledBallots += result.getSpoiledBallots();
        if (result instanceof CountyMMResult) {
            CountyMMResult cr = (CountyMMResult) result;
            addVotes(cr.getVotes());
        } else {
            DistrictMMResult dr = (DistrictMMResult) result;
            addVotes(dr.getVotes());
        }
    }



    private void addVotes(List<PartyVote> votes) {
        votes.stream()
                .forEach(v -> partyVotecount.merge(v.getParty(), v.getVoteCount(), Long::sum));
    }

    public Map<Party, Long> getPartyVotecount() {
        return partyVotecount;
    }

    public Map<Party, Long> getPartyMandates() {
        return partyMandates;
    }

    public Long getTotalBallots() {
        return totalBallots;
    }

    public Long getSpoiledBallots() {
        return spoiledBallots;
    }

    public List<DistrictMMResult> getResults() {
        return results;
    }
}
