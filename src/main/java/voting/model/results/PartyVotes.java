package voting.model.results;

import voting.model.Party;

import javax.persistence.*;

/**
 * Created by andrius on 2/18/17.
 */

@Entity
@Table(name="PARTY_VOTES")
@PrimaryKeyJoinColumn(name="id")
public class PartyVotes extends UnitVotes {

    @ManyToOne(
            cascade = {},
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "party_id", nullable = true)
    private Party party;

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }
}
