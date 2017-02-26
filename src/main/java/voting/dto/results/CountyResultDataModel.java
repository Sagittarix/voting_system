package voting.dto.results;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by andrius on 1/24/17.
 */
public class CountyResultDataModel {

    @NotNull(message = "Trūksta sugadintų biuletenių skaičiaus")
    @Min(value = 0, message = "Sugadinti biuleteniai. Negali būti neigiamas skaičius")
    @Max(value = 500000, message = "Sugadinti biuleteniai. Negali būti tiek daug")
    private int spoiledBallots;

    @NotNull(message = "Spring - Mandato tipas nenurodytas")
    private boolean singleMandateSystem;

    @Valid
    @NotNull(message = "Spring - balsavimo duomenys privalomi")
    @JsonProperty("unitVotes")
    private List<UnitVotesDataModel> unitVotesDataModelsList;

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

    public List<UnitVotesDataModel> getUnitVotesDataModelsList() {
        return unitVotesDataModelsList;
    }

    public void setUnitVotesDataModelsList(List<UnitVotesDataModel> unitVotesDataModelsList) {
        this.unitVotesDataModelsList = unitVotesDataModelsList;
    }

    public Long getCountyId() {
        return countyId;
    }

    public void setCountyId(Long countyId) {
        this.countyId = countyId;
    }
}
