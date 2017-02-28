package voting.dto.results;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by andrius on 1/24/17.
 */

public class VoteListDTO {

    @Valid
    @NotNull
    List<VoteDTO> voteList;

    public VoteListDTO() {
    }

    public List<VoteDTO> getVoteList() {
        return voteList;
    }

    public void setVoteList(List<VoteDTO> voteList) {
        this.voteList = voteList;
    }
}
