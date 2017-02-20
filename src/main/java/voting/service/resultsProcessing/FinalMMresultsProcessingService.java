package voting.service.resultsProcessing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import voting.model.Party;
import voting.repository.DistrictRepository;
import voting.repository.PartyRepository;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by andrius on 2/20/17.
 */

@Service
public class FinalMMresultsProcessingService {

    private final DistrictResultsProcessingService DRPS;
    private final DistrictRepository districtRepository;
    private final PartyRepository partyRepository;

    @Autowired
    public FinalMMresultsProcessingService(DistrictResultsProcessingService DRPS,
                                           DistrictRepository districtRepository,
                                           PartyRepository partyRepository) {
        this.DRPS = DRPS;
        this.districtRepository = districtRepository;
        this.partyRepository = partyRepository;
    }

    public Map<Party, Long> getFinalPartiesWithVotes() {
        Map<Party, Long> mappedParties = new HashMap<>();
        List<Map<Party, Long>> listOfMaps = new ArrayList<>();

        partyRepository.findAll().spliterator().forEachRemaining(p -> mappedParties.put(p, 0L));
        districtRepository.findAll().forEach(d -> listOfMaps.add(DRPS.getPartiesWithVotes(d)));

        listOfMaps.forEach(map -> {
            map.forEach((k, v) -> mappedParties.put(k, (mappedParties.get(k) + v)));
        });

        return mappedParties;
    }

    public Map<Party, Double> getFinalPartiesWithSharePercentage() {
        Map<Party, Long> finalPartiesWithVotes = getFinalPartiesWithVotes();
        Long grandTotalVotes = finalPartiesWithVotes.entrySet().stream()
                .mapToLong(Map.Entry::getValue)
                .sum();

        return finalPartiesWithVotes
                .entrySet()
                .stream()
                .map(e -> new AbstractMap.SimpleEntry<Party, Double>(e.getKey(), e.getValue() / (double) grandTotalVotes))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<Party, Double> getFinalPartiesWithWonMandates() {
        Long blackHoles = 141L;
        return getFinalPartiesWithSharePercentage()
                .entrySet()
                .stream()
                .filter(e -> e.getValue() > 0.05)
                .map(e -> new AbstractMap.SimpleEntry<Party, Double>(e.getKey(), blackHoles / e.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
