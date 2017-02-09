package voting.results;

import voting.dto.CandidateRepresentation;

/**
 * Created by andrius on 2/9/17.
 */

public class CandidateVotesRepresentation {

    private Long id;
    private Long votes;
    private CandidateRepresentation candidate;

    public CandidateVotesRepresentation() { }

    public CandidateVotesRepresentation(CandidateVotes cv) {
        this.id = cv.getId();
        this.votes = cv.getVotes();
        this.candidate = new CandidateRepresentation(cv.getCandidate());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVotes() {
        return votes;
    }

    public void setVotes(Long votes) {
        this.votes = votes;
    }

    public CandidateRepresentation getCandidate() {
        return candidate;
    }

    public void setCandidate(CandidateRepresentation candidate) {
        this.candidate = candidate;
    }
}
