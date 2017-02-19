package voting.dto.results;

import voting.dto.CandidateRepresentation;
import voting.model.results.CandidateVotes;

/**
 * Created by andrius on 2/9/17.
 */

public class CandidateVotesRepresentation extends UnitVotesRepresentation{

    private CandidateRepresentation candidate;

    public CandidateVotesRepresentation() { }

    public CandidateVotesRepresentation(CandidateVotes cv) {
        this.id = cv.getId();
        this.votes = cv.getVotes();
        this.candidate = new CandidateRepresentation(cv.getCandidate());
    }

    public CandidateRepresentation getCandidate() {
        return candidate;
    }

    public void setCandidate(CandidateRepresentation candidate) {
        this.candidate = candidate;
    }
}
