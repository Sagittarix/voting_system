package voting.model.results;

import voting.model.Candidate;

import javax.persistence.*;

/**
 * Created by andrius on 1/24/17.
 */

@Entity
@Table(name="CANDIDATE_VOTES")
@PrimaryKeyJoinColumn(name="id")
public class CandidateVotes extends UnitVotes {

    @ManyToOne(
            cascade = {},
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "candidate_id", nullable = false)
    private Candidate candidate;

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }
}
