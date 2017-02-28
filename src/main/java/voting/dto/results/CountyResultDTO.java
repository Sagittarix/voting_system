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
public class CountyResultDTO {

    @NotNull(message = "Trūksta sugadintų biuletenių skaičiaus")
    @Min(value = 0, message = "Sugadinti biuleteniai. Negali būti neigiamas skaičius")
    @Max(value = 500000, message = "Sugadinti biuleteniai. Negali būti tiek daug")
    private Long spoiledBallots;

    @Valid
    @NotNull(message = "Spring - balsavimo duomenys privalomi")
    @JsonProperty("unitVotes")
    private List<VoteDTO> voteList;

    @NotNull(message = "Spring - Apylinkė privaloma")
    private Long countyId;


    public CountyResultDTO() {
    }

    public CountyResultDTO(Long spoiledBallots, List<VoteDTO> voteList, Long countyId) {
        this.spoiledBallots = spoiledBallots;
        this.voteList = voteList;
        this.countyId = countyId;
    }

    public Long getSpoiledBallots() {
        return spoiledBallots;
    }

    public void setSpoiledBallots(Long spoiledBallots) {
        this.spoiledBallots = spoiledBallots;
    }

    public List<VoteDTO> getVoteList() {
        return voteList;
    }

    public void setVoteList(List<VoteDTO> voteList) {
        this.voteList = voteList;
    }

    public Long getCountyId() {
        return countyId;
    }

    public void setCountyId(Long countyId) {
        this.countyId = countyId;
    }
}
