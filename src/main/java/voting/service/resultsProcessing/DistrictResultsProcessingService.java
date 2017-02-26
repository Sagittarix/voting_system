package voting.service.resultsProcessing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import voting.model.Candidate;
import voting.model.County;
import voting.model.District;
import voting.model.Party;
import voting.model.results.CountyResult;
import voting.service.PartyService;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by andrius on 2/19/17.
 */

@Service
public class DistrictResultsProcessingService {

    private CountyResultsProcessingService CRPS;
    private PartyService partyService;

    @Autowired
    public DistrictResultsProcessingService(CountyResultsProcessingService CRPS,
                                            PartyService partyService) {
        this.CRPS = CRPS;
        this.partyService = partyService;
    }

    public Long getVotersTurnout(District district) {
        return district.getCounties().stream()
                                     .mapToLong(c -> CRPS.getVotersTurnout(c))
                                     .sum();
    }

    public Double getVotersTurnoutInPercentage(District district) {
        Long votes = getVotersTurnout(district);
        Long population = district.getCounties().stream()
                                                .mapToLong(County::getVoterCount)
                                                .sum();
        return votes / (double) population;
    }

    public Map<Party, Long> getPartiesWithVotes(District district) {
        List<Party> parties = partyService.getParties();
        Map<Party, Long> mappedParties = StreamSupport.stream(parties.spliterator(), false)
                                                      .collect(Collectors.toMap(Function.identity(), v -> 0L));

        district.getCounties().forEach(c -> {
            parties.forEach(p -> {
                Long votes = CRPS.getPartyVotes(p, c);
                Long currentVotes = mappedParties.get(p);
                mappedParties.put(p, currentVotes + votes);
            });

        });

        return mappedParties;
    }

    public Long getPartyVotes(Party party, District district) {
        return getPartiesWithVotes(district).get(party);
    }

    public double getPartyVotesOutOfTotalVotesInPercentage(Party party, District district) {
        Long votes = district.getCounties().stream()
                                           .mapToLong(c -> CRPS.getPartyVotes(party, c))
                                           .sum();
        Long turnout = district.getCounties().stream()
                                             .mapToLong(c -> CRPS.getVotersTurnout(c))
                                             .sum();

        return votes / (double) turnout;
    }

    public double getPartyVotesOutOfValidVotesInPercentage(Party party, District district) {
        Long votes = district.getCounties().stream()
                                           .mapToLong(c -> CRPS.getPartyVotes(party, c))
                                           .sum();
        Long valid = district.getCounties().stream()
                                           .mapToLong(c -> CRPS.getVotesFromCountyResult(CRPS.getMMresult(c)))
                                           .sum();

        return votes / (double) valid;
    }

    // get spoiled ballots either for SM or MM results
    public Long getSpoiledBallots(District district, boolean isSingleMandate) {
        return district.getCounties().stream()
                                     .mapToLong(c -> {
                                        CountyResult cr = (isSingleMandate) ? CRPS.getSMresult(c) : CRPS.getMMresult(c);
                                        return cr.getSpoiledBallots();
                                     })
                                     .sum();
    }

    public Long getCandidateVotes(Candidate candidate, District district) {
        return district.getCounties().stream()
                                     .mapToLong(c -> CRPS.getCandidateVotes(candidate, c))
                                     .sum();
    }

    public double getCandidateVotesOutOfTotalVotesInPercentage(Candidate candidate, District district) {
        Long votes = getCandidateVotes(candidate, district);
        Long total = district.getCounties().stream().mapToLong(CRPS::getVotersTurnout).sum();

        return votes / (double) total;
    }

    public double getCandidateVotesOutOfValidVotesInPercentage(Candidate candidate, District district) {
        Long votes = getCandidateVotes(candidate, district);
        Long valid = district.getCounties().stream()
                                           .mapToLong(c -> CRPS.getVotesFromCountyResult(CRPS.getSMresult(c)))
                                           .sum();

        return votes / (double) valid;
    }

    public List<County> getCountiesWithBothResults(District district) {
        return district.getCounties().stream()
                                     .filter(c -> c.getCountyResultList().size() == 2)
                                     .collect(Collectors.toList());
    }

    public List<County> getCountiesWithBothResultsConfirmed(District district) {
        return getCountiesWithBothResults(district).stream()
                                                   .filter(c -> {
                                                        return c.getCountyResultList().stream()
                                                                                      .allMatch(CountyResult::isConfirmed);
                                                   })
                                                   .collect(Collectors.toList());
    }

}
