package voting.results;

/**
 * Created by andrius on 1/24/17.
 */
public class CandidateVotesDataModel {

    private Long id;
    private Long count;
    private CountyResult countyResult;
    private Long candidateId;

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

    public Long getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Long candidateId) {
        this.candidateId = candidateId;
    }
}
