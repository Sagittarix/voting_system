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
        Candidate candidate = new Candidate(candidateData.getPersonId(), candidateData.getFirstName(),
                candidateData.getLastName());
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
     * Checks whether there is no conflict between existing and updated candidate.
     * Assumes both have same PersonID.
     * Throws IllegalArgument if candidates have different names, belongs to different parties or districts
     * @param newCandidateData - must have first/last name, personID, party and optionally district fields set
     * @param oldCandidate - existing candidate
     */
    @Override
    public void checkCandidateIntegrity(CandidateData newCandidateData, Candidate oldCandidate) {
        if (!oldCandidate.getFirstName().equals(newCandidateData.getFirstName())
                || !oldCandidate.getLastName().equals(newCandidateData.getLastName())) {
            throw (new IllegalArgumentException(
                    String.format("Name mismatch: candidate with pid %s already exists and his name is %s %s",
                            oldCandidate.getPersonId(), oldCandidate.getFirstName(), oldCandidate.getFirstName())
            ));
        }
        if (oldCandidate.getDistrict() != null && newCandidateData.getDistrctName() != null) {
            throw (new IllegalArgumentException(
                    String.format("Data mismatch: candidate %s is bound to another district - %s",
                            oldCandidate, oldCandidate.getDistrict()
                    )));
        }
        if (oldCandidate.getParty() != null && oldCandidate.getParty().getName() != newCandidateData.getPartyName()) {
            throw (new IllegalArgumentException(
                    String.format("Data mismatch: candidate %s is bound to another party - %s",
                            oldCandidate, oldCandidate.getParty())
            ));
        }
    }

}
