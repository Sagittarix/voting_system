package voting.dto.results;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by andrius on 1/24/17.
 */

public class UnitVotesDataModel {

    @NotNull(message = "Spring - Trūksta balsų skaičiaus")
    @Min(value = 0, message = "Spring - Balsu skaičius privalo būti teigiamas")
    @Max(value = 500000, message = "Spring - Skirtų balsų negali būti tiek daug")
    private Long votes;

    @NotNull(message = "Spring - Balsavimo objekto identifikatorius privalomas")
    private Long unitId;

    public Long getVotes() {
        return votes;
    }

    public void setVotes(Long votes) {
        this.votes = votes;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }
}
