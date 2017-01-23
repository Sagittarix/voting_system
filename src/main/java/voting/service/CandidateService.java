package voting.service;

import voting.dto.CandidateData;
import voting.model.Candidate;

import java.util.List;

/**
 * Created by domas on 1/10/17.
 */
public interface CandidateService {

    Candidate addNewCandidate(CandidateData candidateData);

    void deleteCandidate(Long id);

    Candidate getCandidate(Long id);

    Candidate getCandidate(String personId);

    List<Candidate> getCandidates();

}
