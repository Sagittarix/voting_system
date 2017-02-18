package voting.dto.results;

import voting.dto.PartyRepresentation;
import voting.model.results.PartyVotes;

/**
 * Created by andrius on 2/18/17.
 */

public class PartyVotesRepresentation extends UnitVotesRepresentation {

    private PartyRepresentation party;

    public PartyVotesRepresentation() { }

    public PartyVotesRepresentation(PartyVotes pv) {
        this.id = pv.getId();
        this.votes = pv.getVotes();
        this.party = new PartyRepresentation(pv.getParty());
    }

    public PartyRepresentation getParty() {
        return party;
    }

    public void setParty(PartyRepresentation party) {
        this.party = party;
    }
}
