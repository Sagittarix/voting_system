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

}
