package voting.dto.results;

import voting.dto.party.PartyShortDTO;
import voting.model.Party;

/**
 * Created by domas on 3/14/17.
 */
public class PartyMandatesDTO {

    PartyShortDTO party;
    Long mandates;

    public PartyMandatesDTO(Party party, Long mandates) {
        this.party = new PartyShortDTO(party);
        this.mandates = mandates;
    }

    public PartyShortDTO getParty() {
        return party;
    }

    public Long getMandates() {
        return mandates;
    }
}
