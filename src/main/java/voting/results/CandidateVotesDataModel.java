package voting.results;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by andrius on 1/24/17.
 */

public class CandidateVotesDataModel {

    @NotNull(message = "Spring - Trūksta kandidato balsų skaičiaus")
    @Min(value = 100, message = "Spring - Kandidato balsai. Negali būti < 100")
    @Max(value = 500000, message = "Spring - Kandidato balsai. Negali būti tiek daug")
    private Long votes;

    @NotNull(message = "Spring - Kandidato identifikatorius privalomas")
    private Long candidateId;

    public Long getVotes() {
        return votes;
    }

    public void setVotes(Long votes) {
        this.votes = votes;
    }

    public Long getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Long candidateId) {
        this.candidateId = candidateId;
    }
}
