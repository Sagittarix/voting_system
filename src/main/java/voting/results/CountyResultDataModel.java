package voting.results;

import java.util.List;

/**
 * Created by andrius on 1/24/17.
 */
public class CountyResultDataModel {

    private Long id;
    private int spoiledBallots;
    private boolean singleMandateSystem;
    private List<CandidateVotesDataModel> candidateVotesDataModelsList;
    private Long county_id;

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

    public List<CandidateVotesDataModel> getCandidateVotesDataModelsList() {
        return candidateVotesDataModelsList;
    }

    public void setCandidateVotesDataModelsList(List<CandidateVotesDataModel> candidateVotesDataModelsList) {
        this.candidateVotesDataModelsList = candidateVotesDataModelsList;
    }

    public Long getCounty_id() {
        return county_id;
    }

    public void setCounty_id(Long county_id) {
        this.county_id = county_id;
    }
}
