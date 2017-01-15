package voting.service;

import voting.model.District;
import voting.model.DistrictData;
import voting.model.Party;
import voting.model.PartyData;

import java.util.List;

/**
 * Created by domas on 1/10/17.
 */
public interface PartyService {

    Party addNewParty(PartyData partyData);

//    Party updateParty(PartyData partyData);

    void deleteParty(Long id);

    Party getParty(Long id);

    List<Party> getParties();
}
