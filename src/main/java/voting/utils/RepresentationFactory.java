//package voting.utils;
//
//import org.springframework.stereotype.Component;
//import voting.dto.CandidateRepresentation;
//import voting.dto.PartyRepresentation;
//import voting.dto.results.CandidateVoteRepresentation;
//import voting.dto.results.PartyVoteRepresentation;
//import voting.dto.results.UnitVoteRepresentation;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by andrius on 2/9/17.
// */
//
//@Component
//public class RepresentationFactory {
//
//    public static CountyResultRepresentation makeRepresentationOf(CountyResult cr) {
//        CountyResultRepresentation crr = new CountyResultRepresentation();
//        crr.setId(cr.getId());
//        crr.setSpoiledBallots(cr.getSpoiledBallots());
//        crr.setSingleMandateSystem(cr.isSingleMandateSystem());
//        crr.setCreatedOn(cr.getCreatedOn());
//        crr.setCountyId(cr.getCounty().getId());
//        crr.setConfirmed(cr.isConfirmed());
//        crr.setConfirmedOn(cr.getConfirmedOn());
//        crr.setUnitVotesList(makeUnitsVotesListRepresentation(cr.getUnitVotesList()));
//        return crr;
//    }
//
//    public static List<CountyResultRepresentation> makeRepresentationOfCountyResultsList(List<CountyResult> list) {
//        List<CountyResultRepresentation> crrl = new ArrayList<>();
//        list.forEach(cr -> crrl.add(makeRepresentationOf(cr)));
//        return crrl;
//    }
//
//    public static CandidateVoteRepresentation makeRepresentationOf(CandidateVote cv) {
//        CandidateVoteRepresentation cvr = new CandidateVoteRepresentation();
//        cvr.setId(cv.getId());
//        cvr.setVoteCount(cv.getVoteCount());
//        cvr.setCandidate(new CandidateRepresentation(cv.getCandidate()));
//
//        return cvr;
//    }
//
//    public static PartyVoteRepresentation makeRepresentationOf(PartyVote cv) {
//        PartyVoteRepresentation pvr = new PartyVoteRepresentation();
//        pvr.setId(cv.getId());
//        pvr.setVoteCount(cv.getVoteCount());
//        pvr.setParty(new PartyRepresentation(cv.getParty()));
//        return pvr;
//    }
//
//    public static List<UnitVoteRepresentation> makeUnitsVotesListRepresentation(List<Vote> unitVotesList) {
//        List<UnitVoteRepresentation> representations = new ArrayList<>();
//        if (CandidateVote.class.isAssignableFrom(unitVotesList.get(0).getClass())) {
//            unitVotesList.forEach(uv -> representations.add(makeRepresentationOf((CandidateVote)uv)));
//        } else {
//            unitVotesList.forEach(uv -> {
//               representations.add(makeRepresentationOf((PartyVote)uv));
//            });
//        }
//        return representations;
//    }
//}
//
