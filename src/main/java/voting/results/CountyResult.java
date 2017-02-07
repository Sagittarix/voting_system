package voting.results;

import voting.model.County;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by andrius on 1/24/17.
 */

@Entity
public class CountyResult {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int spoiledBallots;
    private Date createdOn;

    @OneToMany(
            cascade = {CascadeType.PERSIST},
            fetch = FetchType.LAZY,
            mappedBy = "countyResult"
    )
    private List<CandidateVotes> candidateVotesList;

    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = {}
    )
    @JoinColumn(name = "county_id", nullable = false)
    private County county;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSpoiledBallots() {
        return spoiledBallots;
    }

    public void setSpoiledBallots(int spoiledBallots) {
        this.spoiledBallots = spoiledBallots;
    }

    public List<CandidateVotes> getCandidateVotesList() {
        return candidateVotesList;
    }

    public void setCandidateVotesList(List<CandidateVotes> candidateVotesList) {
        this.candidateVotesList = candidateVotesList;
    }

    public County getCounty() {
        return county;
    }

    public void setCounty(County county) {
        this.county = county;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
}
