package voting.dto.results;

import voting.dto.candidate.CandidateShortDTO;
import voting.results.model.result.ConsolidatedResults;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by domas on 3/14/17.
 */
public class ConsolidatedResultsDTO {


    private List<PartyMandatesDTO> partyMandates;
    private List<CandidateShortDTO> electedCandidates;
    private Long selfServingElectees;

    private int completedSmResults;
    private int completedMmResults;
    private int totalDistricts;

    public ConsolidatedResultsDTO(ConsolidatedResults consolidated) {
        this.partyMandates = consolidated.getTotalPartyMandates().entrySet().stream()
                .map(e -> new PartyMandatesDTO(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
        this.electedCandidates = consolidated.getElectedCandidates().stream()
                .map(CandidateShortDTO::new)
                .collect(Collectors.toList());
        this.selfServingElectees = consolidated.getSelfServingElectees();
        this.completedSmResults = consolidated.getCompletedSmResults();
        this.completedMmResults = consolidated.getCompletedMmResults();
        this.totalDistricts = consolidated.getTotalDistricts();
    }

    public List<PartyMandatesDTO> getPartyMandates() {
        return partyMandates;
    }

    public List<CandidateShortDTO> getElectedCandidates() {
        return electedCandidates;
    }

    public int getCompletedSmResults() {
        return completedSmResults;
    }

    public Long getSelfServingElectees() {
        return selfServingElectees;
    }

    public int getCompletedMmResults() {
        return completedMmResults;
    }

    public int getTotalDistricts() {
        return totalDistricts;
    }
}
