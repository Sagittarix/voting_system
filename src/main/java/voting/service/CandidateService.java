package voting.service;

import voting.model.Candidate;
import voting.model.CandidateData;
import voting.model.District;

import java.util.List;

/**
 * Created by domas on 1/10/17.
 */
public interface CandidateService {

    Candidate addNewCandidate(CandidateData candidateData);

//    Candidate updateCandidate(CandidateData candidateData);

    void deleteCandidate(Long id);

    Candidate getCandidate(Long id);

    List<Candidate> getCandidates();

}
