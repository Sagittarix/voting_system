package voting.dto.results;

import voting.dto.PartyShortRepresentation;
import voting.results.model.votecount.PartyVote;

/**
 * Created by andrius on 2/18/17.
 */

public class PartyVoteRepresentation {

    private PartyShortRepresentation party;
    private Long voteCount;

    public PartyVoteRepresentation() { }

    public PartyVoteRepresentation(PartyVote pv) {
        party = new PartyShortRepresentation(pv.getParty());
        voteCount = pv.getVoteCount();
    }

    public PartyShortRepresentation getParty() {
        return party;
    }

    public void setParty(PartyShortRepresentation party) {
        this.party = party;
    }

    public Long getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Long voteCount) {
        this.voteCount = voteCount;
    }
}
