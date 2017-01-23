package voting.service;

import voting.dto.CandidateData;
import voting.model.District;
import voting.model.Party;
import voting.dto.PartyData;

import java.util.List;

/**
 * Created by domas on 1/10/17.
 */
public interface PartyService {

    Party addNewParty(PartyData partyData);

    void deleteParty(Long id);

    Party getParty(Long id);

    List<Party> getParties();

    Party addCandidateList(Long id, List<CandidateData> candidateListData);

    void deleteCandidateList(Long id);
}
