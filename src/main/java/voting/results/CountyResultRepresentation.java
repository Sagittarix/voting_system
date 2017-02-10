package voting.results;

import voting.dto.CountyRepresentation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by andrius on 2/9/17.
 */

public class CountyResultRepresentation {

    private Long id;
    private int spoiledBallots;
    private boolean singleMandateSystem;
    private Date createdOn;
    private List<CandidateVotesRepresentation> candidateVotesList;
    private CountyRepresentation county;

    public CountyResultRepresentation() { }

    public CountyResultRepresentation(CountyResult cr) {
        this.id = cr.getId();
        this.spoiledBallots = cr.getSpoiledBallots();
        this.singleMandateSystem = cr.isSingleMandateSystem();
        this.createdOn = cr.getCreatedOn();
        this.candidateVotesList = new ArrayList<>();
        this.county = new CountyRepresentation(cr.getCounty());
        cr.getCandidateVotesList().forEach(cv -> candidateVotesList.add(new CandidateVotesRepresentation(cv)));
    }

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

    public boolean isSingleMandateSystem() {
        return singleMandateSystem;
    }

    public void setSingleMandateSystem(boolean singleMandateSystem) {
        this.singleMandateSystem = singleMandateSystem;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public List<CandidateVotesRepresentation> getCandidateVotesList() {
        return candidateVotesList;
    }

    public void setCandidateVotesList(List<CandidateVotesRepresentation> candidateVotesList) {
        this.candidateVotesList = candidateVotesList;
    }

    public CountyRepresentation getCounty() {
        return county;
    }

    public void setCounty(CountyRepresentation county) {
        this.county = county;
    }
}
