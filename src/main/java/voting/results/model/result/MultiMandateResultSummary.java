package voting.results.model.result;

import voting.model.Party;
import voting.results.model.votecount.PartyVote;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by domas on 3/9/17.
 */
public class MultiMandateResultSummary {

    private Map<Party, Long> partyVotecount;
    private Map<Party, Long> partyMandates;
    private Long totalBallots = 0L;
    private Long spoiledBallots = 0L;
    private List<DistrictMMResult> results;
    private Long availableMandates;


    public MultiMandateResultSummary(List<Party> parties, List<DistrictMMResult> results) {
        this.totalBallots = 0L;
        this.spoiledBallots = 0L;
        this.partyVotecount = parties.stream().collect(Collectors.toMap(p -> p, p -> 0L));
        this.partyMandates = parties.stream().collect(Collectors.toMap(p -> p, p -> 0L));
        this.availableMandates = 141L - results.size();
        this.results = results;
        this.results.forEach(this::combineResults);
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
        computePartyMandates();
    }

    private void computePartyMandates() {
        Map<Party, Double> mandateFractions = new HashMap<>();

        partyVotecount.forEach((party, votecount) -> {
            Double floatMandates = computeMandates(votecount);
            Long longMandates = floatMandates.longValue();
            partyMandates.put(party, longMandates);
            mandateFractions.put(party, floatMandates - longMandates);
        });

        Long mandatesGiven = partyMandates.values().stream().reduce(Long::sum).get();
        Long mandatesRemaining = availableMandates - mandatesGiven;

        if (totalBallots != 0) {
            mandateFractions.entrySet().stream()
                    .sorted(Map.Entry.<Party, Double>comparingByValue().reversed())
                    .limit(mandatesRemaining)
                    .map(Map.Entry::getKey)
                    .forEach(party -> partyMandates.merge(party, 1L, Long::sum));
        }
    }

    private Double computeMandates(Long votecount) {
        if (totalBallots == 0) {
            return 0d;
        }
        double percentOfAllVotes = (double) votecount / totalBallots;
        return percentOfAllVotes > 0.05d ?
               percentOfAllVotes * availableMandates :
               0d;
    }

    public void setResults(List<DistrictMMResult> results) {
        this.results = results;
    }

    private void addVotes(List<PartyVote> votes) {
        votes.forEach(v -> partyVotecount.merge(v.getParty(), v.getVoteCount(), Long::sum));
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
