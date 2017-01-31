package voting.results;

import voting.model.Candidate;
import javax.persistence.*;

/**
 * Created by andrius on 1/24/17.
 */

@Entity
public class CandidateVotes {

    @Id
    @GeneratedValue
    private Long id;
    private Long count;

    @ManyToOne(
            cascade = {},
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "county_result_id", nullable = false)
    private CountyResult countyResult;

    @ManyToOne(
            cascade = {},
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "candidate_id", nullable = false)
    private Candidate candidate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public CountyResult getCountyResult() {
        return countyResult;
    }

    public void setCountyResult(CountyResult countyResult) {
        this.countyResult = countyResult;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }
}
