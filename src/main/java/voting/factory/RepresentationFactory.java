package voting.factory;

import org.springframework.stereotype.Component;
import voting.dto.CandidateRepresentation;
import voting.dto.PartyRepresentation;
import voting.dto.results.CandidateVotesRepresentation;
import voting.dto.results.CountyResultRepresentation;
import voting.dto.results.PartyVotesRepresentation;
import voting.dto.results.UnitVotesRepresentation;
import voting.model.results.CandidateVotes;
import voting.model.results.CountyResult;
import voting.model.results.PartyVotes;
import voting.model.results.UnitVotes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrius on 2/9/17.
 */

@Component
public class RepresentationFactory {

    public static CountyResultRepresentation makeRepresentationOf(CountyResult cr) {
        CountyResultRepresentation crr = new CountyResultRepresentation();
        crr.setId(cr.getId());
        crr.setSpoiledBallots(cr.getSpoiledBallots());
        crr.setSingleMandateSystem(cr.isSingleMandateSystem());
        crr.setCreatedOn(cr.getCreatedOn());
        crr.setCountyId(cr.getCounty().getId());
        crr.setConfirmed(cr.isConfirmed());
        crr.setConfirmedOn(cr.getConfirmedOn());
        crr.setUnitVotesList(makeUnitsVotesListRepresentation(cr.getUnitVotesList()));
        return crr;
    }

    public static List<CountyResultRepresentation> makeRepresentationOfCountyResultsList(List<CountyResult> list) {
        List<CountyResultRepresentation> crrl = new ArrayList<>();
        list.forEach(cr -> crrl.add(makeRepresentationOf(cr)));
        return crrl;
    }

    public static CandidateVotesRepresentation makeRepresentationOf(CandidateVotes cv) {
        CandidateVotesRepresentation cvr = new CandidateVotesRepresentation();
        cvr.setId(cv.getId());
        cvr.setVotes(cv.getVotes());
        cvr.setCandidate(new CandidateRepresentation(cv.getCandidate()));

        return cvr;
    }

    public static PartyVotesRepresentation makeRepresentationOf(PartyVotes cv) {
        PartyVotesRepresentation pvr = new PartyVotesRepresentation();
        pvr.setId(cv.getId());
        pvr.setVotes(cv.getVotes());
        pvr.setParty(new PartyRepresentation(cv.getParty()));
        return pvr;
    }

    public static List<UnitVotesRepresentation> makeUnitsVotesListRepresentation(List<UnitVotes> unitVotesList) {
        List<UnitVotesRepresentation> representations = new ArrayList<>();
        if (CandidateVotes.class.isAssignableFrom(unitVotesList.get(0).getClass())) {
            unitVotesList.forEach(uv -> representations.add(makeRepresentationOf((CandidateVotes)uv)));
        } else {
            unitVotesList.forEach(uv -> {
               representations.add(makeRepresentationOf((PartyVotes)uv));
            });
        }
        return representations;
    }

    /*public static UnitVotesRepresentation makeRepresentationOf(UnitVotes uv) {
        if (CandidateVotes.class.isAssignableFrom(uv.getClass())) {
            CandidateVotesRepresentation cvr = new CandidateVotesRepresentation();
            cvr.setId(uv.getId());
            cvr.setVotes(uv.getVotes());
            cvr.setCandidate(new CandidateRepresentation(uv.getCandidate()));
        }
    }*/

    /*public static UnitVotesRepresentation makeCandidateVotesListRepresentation(CandidateVotes list) {
        CandidateVotesRepresentation cvr = new CandidateVotesRepresentation();
        cvr.setId(cv.getId());
        cvr.setVotes(cv.getVotes());
        cvr.setParty(new CandidateRepresentation(cv.getCandidate()));

        return cvr;
    }*/

    /*public static List<CandidateVotesRepresentation> makeRepresentationOf(List<CandidateVotes> rawList) {
        List<CandidateVotesRepresentation> cvrl = new ArrayList<>();
        rawList.forEach(cv -> {
            cvrl.add(makeRepresentationOf(cv));
        });

        return cvrl;
    }*/
}

