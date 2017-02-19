package voting.service.results;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import voting.dto.results.UnitVotesDataModel;
import voting.model.results.CandidateVotes;
import voting.model.results.CountyResult;
import voting.model.results.PartyVotes;
import voting.model.results.UnitVotes;
import voting.repository.CandidateRepository;
import voting.repository.PartyRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrius on 1/24/17.
 */

@Service
public class UnitVotesService {

    private final CandidateRepository candidateRepository;
    private final PartyRepository partyRepository;

    @Autowired
    public UnitVotesService(CandidateRepository candidateRepository, PartyRepository partyRepository) {
        this.candidateRepository = candidateRepository;
        this.partyRepository = partyRepository;
    }

    public List<UnitVotes> mapCollectionDataToEntities(
            List<UnitVotesDataModel> list,
            boolean isSingleMandate,
            CountyResult cr) {
        List<UnitVotes> unitVotesList = new ArrayList<>();
        if (isSingleMandate) {
            list.forEach(el -> {
                CandidateVotes cv = new CandidateVotes();
                cv.setVotes(el.getVotes());
                cv.setCountyResult(cr);
                cv.setCandidate(candidateRepository.findOne(el.getUnitId()));
                unitVotesList.add(cv);
            });
        } else {
            list.forEach(el -> {
                PartyVotes pv = new PartyVotes();
                pv.setVotes(el.getVotes());
                pv.setCountyResult(cr);
                pv.setParty(partyRepository.findOne(el.getUnitId()));
                unitVotesList.add(pv);
            });
        }

        return unitVotesList;
    }
}
