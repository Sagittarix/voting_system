package voting.results;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlEnumValue;
import java.util.List;

/**
 * Created by andrius on 1/24/17.
 */
public class CountyResultDataModel {

    @NotNull(message = "Spring - Trūksta sugadintų biuletenių skaičiaus")
    @Min(value = 100, message = "Spring - Sugadinti biuleteniai. Negali būti < 100")
    @Max(value = 500000, message = "Spring - Sugadinti biuleteniai. Negali būti tiek daug")
    private int spoiledBallots;

    @NotNull(message = "Spring - Mandato tipas nenurodytas")
    private boolean singleMandateSystem;

    @Valid
    @NotNull(message = "Spring - kandidatų balsų duomenys privalomi")
    @JsonProperty("candidatesVotes")
    private List<CandidateVotesDataModel> candidateVotesDataModelsList;

    @NotNull(message = "Spring - Apylinkė privaloma")
    private Long countyId;

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

    public Long getCountyId() {
        return countyId;
    }

    public void setCountyId(Long countyId) {
        this.countyId = countyId;
    }
}
