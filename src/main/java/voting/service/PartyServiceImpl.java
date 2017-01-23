package voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import voting.dto.CandidateData;
import voting.model.Candidate;
import voting.model.Party;
import voting.dto.PartyData;
import voting.repository.CandidateRepository;
import voting.repository.PartyRepository;

import java.util.List;

/**
 * Created by domas on 1/15/17.
 */
@Service
public class PartyServiceImpl implements PartyService{

    private PartyRepository partyRepository;
    private CandidateRepository candidateRepository;

    @Autowired
    public PartyServiceImpl(PartyRepository partyRepository, CandidateRepository candidateRepository) {
        this.partyRepository = partyRepository;
        this.candidateRepository = candidateRepository;
    }


    @Override
    public Party addNewParty(PartyData partyData) {
        Party party = new Party(partyData.getName(), partyData.getShortName());
        if (partyData.getCandidatesData() != null) {
            partyData.getCandidatesData().forEach(
                    candidateData -> {
                        // TODO: check if candidate data is valid (if personID exist, check fname, lname, party)
                        // TODO: check if personId doesnt exist in repo, then check that candidate doesnt belond to party
                        Candidate candidate = candidateRepository.findByPersonId(candidateData.getPersonId());
                        if (candidate == null) {
                            candidate = new Candidate(candidateData.getPersonId(), candidateData.getFirstName(),
                                    candidateData.getLastName());
                            candidate = candidateRepository.save(candidate);
                        }
                        party.addCandidate(candidate);
                    });
        }
        return partyRepository.save(party);
    }

    @Override
    public void deleteParty(Long id) {
        partyRepository.delete(id);
    }

    @Override
    public Party getParty(Long id) {
        return partyRepository.findOne(id);
    }

    @Override
    public List<Party> getParties() {
        return (List<Party>) partyRepository.findAll();
    }

    //TODO: implement
    @Override
    public Party addCandidateList(Long id, List<CandidateData> candidateListData) {
        return null;
    }

    //TODO: implement
    @Override
    public void deleteCandidateList(Long id) {

    }
}
