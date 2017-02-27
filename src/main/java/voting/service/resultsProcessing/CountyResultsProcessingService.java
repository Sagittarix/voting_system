//package voting.service.resultsProcessing;
//
//import org.springframework.stereotype.Service;
//import voting.model.Candidate;
//import voting.model.County;
//import voting.model.Party;
//import voting.model.results.CandidateVotes;
//import voting.model.results.CountyResult;
//import voting.model.results.PartyVotes;
//import voting.model.results.UnitVotes;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Created by andrius on 2/18/17.
// */
//
//@Service
//public class CountyResultsProcessingService {
//
//    // get single-mandate result from particular county
//    // NoSuchElementException not caught
//    public CountyResult getSMresult(County county) {
//        return county.getCountyResultList()
//                     .stream()
//                     .filter(CountyResult::isSingleMandateSystem)
//                     .findFirst()
//                     .get();
//    }
//
//    // get single-mandate result from particular county
//    // NoSuchElementException not caught
//    public CountyResult getMMresult(County county) {
//        return county.getCountyResultList()
//                     .stream()
//                     .filter(cr -> !cr.isSingleMandateSystem())
//                     .findFirst()
//                     .get();
//    }
//
//    // get votes count for particular CountyResult
//    public Long getVotesFromCountyResult(CountyResult cr) {
//        return cr.getUnitVotesList()
//                 .stream()
//                 .mapToLong(UnitVotes::getVotes)
//                 .sum();
//    }
//
//    // get voter-turnout in particular county
//    public Long getVotersTurnout(County county) {
//        CountyResult cr = getSMresult(county);
//        return getVotesFromCountyResult(cr) + cr.getSpoiledBallots();
//    }
//
//    // get voter-turnout in % out of population in particular county
//    public Double getVotersTurnoutInPercentage(County county) {
//        return getVotersTurnout(county) / (double)county.getVoterCount();
//    }
//
//    // map each Party with its votes
//    public Map<Party, Long> getPartiesWithVotes(County county) {
//        Map<Party, Long> partiesMap = new HashMap<>();
//        CountyResult mmcr = getMMresult(county);
//        mmcr.getUnitVotesList().stream()
//                               .map(uv -> (PartyVotes)(uv))
//                               .forEach(pv -> {
//                                    partiesMap.putIfAbsent(pv.getParty(), pv.getVotes());
//                               });
//
//        return partiesMap;
//    }
//
//    // get votes for individual Party
//    public Long getPartyVotes(Party party, County county) {
//        return getMMresult(county).getUnitVotesList().stream()
//                                                     .map(uv -> (PartyVotes)uv)
//                                                     .filter(uv -> uv.getParty().equals(party))
//                                                     .findFirst()
//                                                     .get()
//                                                     .getVotes();
//    }
//
//    // MM - percentage of party votes from voters-turnover
//    public Double getPartyVotesOutOfTotalVotesInPercentage(Party party, County county) {
//        Map<Party, Long> partiesWithVotes = getPartiesWithVotes(county);
//        Long votes = partiesWithVotes.get(party);
//        Long total = getVotersTurnout(county);
//
//        return votes / (double) total;
//    }
//
//    // MM - percentage of party votes from all valid bulletins
//    public Double getPartyVotesOutOfValidVotesInPercentage(Party party, County county) {
//        Map<Party, Long> partiesWithVotes = getPartiesWithVotes(county);
//        Long votes = partiesWithVotes.get(party);
//        Long valid = getVotesFromCountyResult(getMMresult(county));
//
//        return votes / (double) valid;
//    }
//
//    // map each Candidate with its votes
//    public Map<Candidate, Long> getCandidatesWithVotes(County county) {
//        Map<Candidate, Long> candidatesMap = new HashMap<>();
//        CountyResult smcr = getSMresult(county);
//        smcr.getUnitVotesList().stream()
//                .map(uv -> (CandidateVotes)(uv))
//                .forEach(cv -> {
//                    candidatesMap.putIfAbsent(cv.getCandidate(), cv.getVotes());
//                });
//
//        return candidatesMap;
//    }
//
//    public Long getCandidateVotes(Candidate candidate, County county) {
//        return getCandidatesWithVotes(county).get(candidate);
//    }
//
//    // get % of individual Candidate votes out of total votes (voter-turnout) in County
//    public Double getCandidateVotesOutOfTotalVotesInPercentage(Candidate candidate, County county) {
//        Map<Candidate, Long> candidatesWithVotes = getCandidatesWithVotes(county);
//        Long votes = candidatesWithVotes.get(candidate);
//        Long total = getVotersTurnout(county);
//
//        return votes / (double) total;
//    }
//
//    // get % of individual Candidate votes out of valid votes (all County votes) in County
//    public Double getCandidateVotesOutOfValidVotesInPercentage(Candidate candidate, County county) {
//        Map<Candidate, Long> candidatesWithVotes = getCandidatesWithVotes(county);
//        Long votes = candidatesWithVotes.get(candidate);
//        Long valid = getVotesFromCountyResult(getSMresult(county));
//
//        return votes / (double) valid;
//    }
//}
