package voting.model.results;

import voting.model.results.CountyResult;

import javax.persistence.*;

/**
 * Created by andrius on 2/18/17.
 */

@Entity
@Table(name = "UNIT_VOTES")
@Inheritance(strategy = InheritanceType.JOINED)
public class UnitVotes {

    @Id
    @GeneratedValue
    private Long id;
    private Long votes;

    @ManyToOne(
            cascade = {},
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "county_result_id", nullable = false)
    private CountyResult countyResult;

    public Long getId() {
        return id;
    }

    public Long getVotes() {
        return votes;
    }

    public void setVotes(Long votes) {
        this.votes = votes;
    }

    public CountyResult getCountyResult() {
        return countyResult;
    }

    public void setCountyResult(CountyResult countyResult) {
        this.countyResult = countyResult;
    }

}
