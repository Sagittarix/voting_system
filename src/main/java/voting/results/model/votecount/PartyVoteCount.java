package voting.results.model.votecount;

import voting.model.Party;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Created by domas on 2/22/17.
 */
@Entity
public class PartyVoteCount extends VoteCount {

    @ManyToOne
    @JoinColumn(name = "party_id")
    private Party party;

    public PartyVoteCount() {
    }

    public PartyVoteCount(Party party, Long votes) {
        super(votes);
        this.party = party;
    }


    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }
}
