package voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import voting.dto.CandidateData;
import voting.exception.NotFoundException;
import voting.model.Candidate;
import voting.repository.CandidateRepository;

import java.util.List;

/**
 * Created by domas on 1/14/17.
 */
@Service
public class CandidateServiceImpl implements CandidateService{

    private CandidateRepository candidateRepository;

    @Autowired
    public CandidateServiceImpl(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }


    @Override
    public Candidate getCandidate(Long id) {
        Candidate candidate = candidateRepository.findOne(id);
        if (candidate == null) {
            throw (new NotFoundException("Couldn't find candidate with id " + id));
        }
        return candidate;
    }

    @Override
    public Candidate getCandidate(String personId) {
        Candidate candidate = candidateRepository.findByPersonId(personId);
        if (candidate == null) {
            throw (new NotFoundException("Couldn't find candidate with personId " + personId));
        }
        return candidate;
    }

    @Override
    public Candidate addNewCandidate(CandidateData candidateData) {
        Candidate candidate = new Candidate(candidateData.getFirstName(), candidateData.getLastName(), candidateData.getPersonId());
        return candidateRepository.save(candidate);
    }

    @Override
    public void deleteCandidate(Long id) {
        Candidate candidate = getCandidate(id);
        candidateRepository.delete(id);
    }

    @Override
    public List<Candidate> getCandidates() {
        return (List<Candidate>) candidateRepository.findAll();
    }


    /**
     * Checks for conflicts between existing and updated candidate.
     * Assumes both have same PersonID.
     * Throws IllegalArgument if candidates have different names, belongs to different parties or districts
     * @param newCandidateData - must have first/last name, personID, party and optionally district fields set
     * @param existingCandidate - persisted candidate
     */
    @Override
    public void checkCandidateIntegrity(CandidateData newCandidateData, Candidate existingCandidate) {
        if (!existingCandidate.getFirstName().equals(newCandidateData.getFirstName())
                || !existingCandidate.getLastName().equals(newCandidateData.getLastName())) {
            throw (new IllegalArgumentException(
                    String.format("Name mismatch: candidate with pid %s already exists and his name is %s %s",
                            existingCandidate.getPersonId(), existingCandidate.getFirstName(), existingCandidate.getFirstName())
            ));
        }
        if (existingCandidate.getDistrict() != null && newCandidateData.getDistrctName() != null) {
            throw (new IllegalArgumentException(
                    String.format("Data mismatch: candidate %s is bound to another district - %s",
                            existingCandidate, existingCandidate.getDistrict()
                    )));
        }
        if (existingCandidate.getParty() != null && existingCandidate.getParty().getName() != newCandidateData.getPartyName()) {
            throw (new IllegalArgumentException(
                    String.format("Data mismatch: candidate %s is bound to another party - %s",
                            existingCandidate, existingCandidate.getParty())
            ));
        }
    }

}
