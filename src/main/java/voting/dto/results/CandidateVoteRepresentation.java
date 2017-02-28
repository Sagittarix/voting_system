package voting.dto.results;

import voting.dto.CandidateShortRepresentation;
import voting.results.model.votecount.CandidateVote;

/**
 * Created by andrius on 2/9/17.
 */

public class CandidateVoteRepresentation {

    private CandidateShortRepresentation candidate;
    private Long voteCount;

    public CandidateVoteRepresentation() { }

    public CandidateVoteRepresentation(CandidateVote cv) {
        candidate = new CandidateShortRepresentation(cv.getCandidate());
        voteCount = cv.getVoteCount();
    }

    public CandidateShortRepresentation getCandidate() {
        return candidate;
    }

    public void setCandidate(CandidateShortRepresentation candidate) {
        this.candidate = candidate;
    }

    public Long getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Long voteCount) {
        this.voteCount = voteCount;
    }
}
