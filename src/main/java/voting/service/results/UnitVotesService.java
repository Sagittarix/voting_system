//package voting.service.results;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import voting.dto.results.UnitVotesDataModel;
//import voting.model.results.CandidateVote;
//import voting.model.results.CountyResult;
//import voting.model.results.PartyVote;
//import voting.model.results.Vote;
//import voting.service.CandidateService;
//import voting.service.PartyService;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by andrius on 1/24/17.
// */
//
//@Service
//public class UnitVotesService {
//
//    private final CandidateService candidateService;
//    private final PartyService partyService;
//
//    @Autowired
//    public UnitVotesService(CandidateService candidateService, PartyService partyService) {
//        this.candidateService = candidateService;
//        this.partyService = partyService;
//    }
//
//    public List<Vote> mapCollectionDataToEntities(
//            List<UnitVotesDataModel> list,
//            boolean isSingleMandate,
//            CountyResult cr) {
//        List<Vote> unitVotesList = new ArrayList<>();
//        if (isSingleMandate) {
//            list.forEach(el -> {
//                CandidateVote cv = new CandidateVote();
//                cv.setVoteCount(el.getVoteCount());
//                cv.setCountyResult(cr);
//                cv.setCandidate(candidateService.getCandidate(el.getUnitId()));
//                unitVotesList.add(cv);
//            });
//        } else {
//            list.forEach(el -> {
//                PartyVote pv = new PartyVote();
//                pv.setVoteCount(el.getVoteCount());
//                pv.setCountyResult(cr);
//                pv.setParty(partyService.getParty(el.getUnitId()));
//                unitVotesList.add(pv);
//            });
//        }
//
//        return unitVotesList;
//    }
//}
