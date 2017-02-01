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

    static public void checkCandidateIntegrity(CandidateData newCandidateData, Candidate oldCandidate) {
        if (!oldCandidate.getFirstName().equals(newCandidateData.getFirstName())
                || !oldCandidate.getLastName().equals(newCandidateData.getLastName())) {
            throw (new IllegalArgumentException(
                    String.format("Name mismatch: candidate with pid %s already exists and his name is %s %s",
                            oldCandidate.getPersonId(), oldCandidate.getFirstName(), oldCandidate.getFirstName())
            ));
        }
        if (oldCandidate.getDistrict() != null) {
            throw (new IllegalArgumentException(
                    String.format("Data mismatch: candidate %s is bound to another district - %s",
                            oldCandidate, oldCandidate.getDistrict()
                    )));
        }
        if (oldCandidate.getParty() != null && oldCandidate.getParty().getId() != newCandidateData.getPartyId()) {
            throw (new IllegalArgumentException(
                    String.format("Data mismatch: candidate %s is bound to another party - %s",
                            oldCandidate, oldCandidate.getParty())
            ));
        }
    }

}
