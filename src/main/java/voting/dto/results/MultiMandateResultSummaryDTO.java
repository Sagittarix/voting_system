package voting.dto.results;

import voting.model.Party;
import voting.results.model.result.MultiMandateResultSummary;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by domas on 3/9/17.
 */
public class MultiMandateResultSummaryDTO {

    private List<PartyResultDTO> partyResults;
    private Long totalBallots = 0L;
    private Long spoiledBallots = 0L;

    private List<DistrictResultSummaryDTO> districtResults;

    public MultiMandateResultSummaryDTO(MultiMandateResultSummary summary) {
        Map<Party, Long> partyVotes = summary.getPartyVotecount();
        Map<Party, Long> partyMandates = summary.getPartyMandates();
        this.totalBallots = summary.getTotalBallots();
        this.spoiledBallots = summary.getSpoiledBallots();
        this.partyResults = partyVotes.keySet().stream()
                .map(p -> new PartyResultDTO(p, partyVotes.get(p), partyMandates.get(p)))
                .collect(Collectors.toList());
        this.districtResults = summary.getResults().stream()
                .map(DistrictResultSummaryDTO::new)
                .collect(Collectors.toList());
    }


    public List<PartyResultDTO> getPartyResults() {
        return partyResults;
    }

    public Long getTotalBallots() {
        return totalBallots;
    }

    public Long getSpoiledBallots() {
        return spoiledBallots;
    }

    public List<DistrictResultSummaryDTO> getDistrictResults() {
        return districtResults;
    }
}
