package voting.results.model.result;

import voting.model.Candidate;
import voting.model.District;
import voting.model.Party;
import voting.results.model.votecount.PartyVote;

import java.util.*;
import java.util.stream.Collectors;

import static voting.utils.Constants.TOTAL_MANDATES;

/**
 * Created by domas on 3/9/17.
 */
public class ConsolidatedResults {

    private int availableMandates;
    private Set<Candidate> smElectedCandidates;
    private Set<Candidate> electedCandidates;

    private Map<Party, Long> partyVotecount;
    private Map<Party, Long> preliminaryMmPartyMandates;
    private Map<Party, Long> partyMandates;

    private int completedSmResults = 0;
    private int completedMmResults = 0;

    private Long validBallots = 0L;
    private Long totalBallots = 0L;
    private Long spoiledBallots = 0L;

    private List<DistrictMMResult> mmResults;
    private int totalDistricts;


    public ConsolidatedResults(List<Party> parties, List<District> districts) {

        this.partyVotecount = parties.stream().collect(Collectors.toMap(p -> p, p -> 0L));
        this.preliminaryMmPartyMandates = parties.stream().collect(Collectors.toMap(p -> p, p -> 0L));
        this.partyMandates = parties.stream().collect(Collectors.toMap(p -> p, p -> 0L));

        this.availableMandates = TOTAL_MANDATES - districts.size();

        this.totalDistricts = districts.size();

        this.mmResults = districts.stream().map(District::getMmResult).collect(Collectors.toList());
        this.mmResults.forEach(this::combineDistrictMmResult);


        this.smElectedCandidates = new HashSet<>();
        this.electedCandidates = new HashSet<>();

        districts.stream()
                .map(District::getSmResult)
                .filter(r -> r!= null)
                .filter(this::resultIsComplete)
                .forEach(this::processCompletedSmResult);
    }

    private void combineDistrictMmResult(DistrictMMResult result) {
        validBallots += result.getValidBallots();
        spoiledBallots += result.getSpoiledBallots();
        totalBallots = validBallots + spoiledBallots;
        addVotes(result.getVotes());
        computePreliminaryMmPartyMandates();

        if (resultIsComplete(result)) {
            processCompletedMmResult(result);
        }
    }

    public void combineCountyMmResult(CountyMMResult result) {
        validBallots += result.getValidBallots();
        spoiledBallots += result.getSpoiledBallots();
        totalBallots = validBallots + spoiledBallots;
        addVotes(result.getVotes());

        computePreliminaryMmPartyMandates();

        // TODO: temp, fix
        DistrictMMResult dr = result.getCounty().getDistrict().getMmResult();
        if (resultIsComplete(dr)) {
            processCompletedMmResult(dr);
        }

    }

    private void computePreliminaryMmPartyMandates() {
        Map<Party, Double> mandateFractions = new HashMap<>();

        partyVotecount.forEach((party, votecount) -> {
            Double floatMandates = computeMandates(votecount);
            Long longMandates = floatMandates.longValue();
            preliminaryMmPartyMandates.put(party, longMandates);
            mandateFractions.put(party, floatMandates - longMandates);
        });

        Long mandatesGiven = preliminaryMmPartyMandates.values().stream().reduce(Long::sum).get();
        Long mandatesRemaining = availableMandates - mandatesGiven;

        if (totalBallots != 0) {
            mandateFractions.entrySet().stream()
                    .sorted(Map.Entry.<Party, Double>comparingByValue().reversed())
                    .limit(mandatesRemaining)
                    .map(Map.Entry::getKey)
                    .forEach(party -> preliminaryMmPartyMandates.merge(party, 1L, Long::sum));
        }

        System.out.println("PRELIM mm MANDATES: " + preliminaryMmPartyMandates);
    }

    private Double computeMandates(Long votecount) {
        if (totalBallots == 0) {
            return 0d;
        }
        double percentOfAllVotes = (double) votecount / totalBallots;
        return percentOfAllVotes > 0.05d ?
               percentOfAllVotes * availableMandates :
               0d;
    }

    private void addVotes(List<PartyVote> votes) {
        votes.forEach(v -> partyVotecount.merge(v.getParty(), v.getVoteCount(), Long::sum));
    }


    public void processCompletedSmResult(DistrictSMResult result) {
        completedSmResults++;
        System.out.println(String.format("SM result complete for %s, completed %d/%d",
                result.getDistrict().getName(), completedSmResults, totalDistricts));

        Candidate winner = result.getVotes().get(0).getCandidate();
        System.out.println("Elected is: " + winner);


        smElectedCandidates.add(winner);
        if (!electedCandidates.add(winner)) {
            // add next candidate from winners party

            System.out.println("Candidate is already elected, searching for net in his party");
            // TODO; padaryti exceptiona, jeigu pritruksta nariu partijoj
            Candidate nextCandidate = winner.getParty().getCandidates().stream()
                    .filter(this::candidateIsNotYetElected)
                    .findFirst()
                    .get();
            System.out.println("Next candidate in list: " + nextCandidate);


            partyMandates.merge(winner.getParty(), 1L, Long::sum);
        }
    }

    private void processCompletedMmResult(DistrictMMResult result) {
        completedMmResults++;
        System.out.println(String.format("MM result complete for %s, completed %d/%d",
                result.getDistrict().getName(), completedMmResults, totalDistricts));

        if (completedMmResults == totalDistricts) {
            System.out.println("MM voting is complete");
            System.out.println("Mandates won in MM voting: " + preliminaryMmPartyMandates);

            addCandidatesElectedInMmVoting();

            System.out.println("ELECTED CANDIDATES: ");
            electedCandidates.forEach(System.out::println);

        }
    }

    private void addCandidatesElectedInMmVoting() {
        preliminaryMmPartyMandates.forEach((party, mandates) -> {
            party.getCandidates().stream()
                    .filter(this::candidateIsNotYetElected)
                    .limit(mandates)
                    .forEach(c -> {
                        System.out.println("Candidate elected, based on mm results: " + c);
                        electedCandidates.add(c);
                    });
        });
    }

    private boolean resultIsComplete(DistrictResult result) {
        return result.getConfirmedCountyResults() == result.getTotalCountyResults();
    }

    private boolean candidateIsNotYetElected(Candidate candidate) {
        return !electedCandidates.contains(candidate);
    }

    public void setMmResults(List<DistrictMMResult> mmResults) {
        this.mmResults = mmResults;
    }

    public Set<Candidate> getElectedCandidates() {
        return electedCandidates;
    }

    public Map<Party, Long> getPreliminaryMmPartyMandates() {
        return preliminaryMmPartyMandates;
    }

    public Map<Party, Long> getPartyVotecount() {
        return partyVotecount;
    }

    public Map<Party, Long> getPartyMandates() {
        return partyMandates;
    }

    public int getCompletedSmResults() {
        return completedSmResults;
    }

    public int getCompletedMmResults() {
        return completedMmResults;
    }

    public Long getValidBallots() {
        return validBallots;
    }

    public Long getTotalBallots() {
        return totalBallots;
    }

    public Long getSpoiledBallots() {
        return spoiledBallots;
    }

    public List<DistrictMMResult> getMmResults() {
        return mmResults;
    }

    public int getTotalDistricts() {
        return totalDistricts;
    }
}
