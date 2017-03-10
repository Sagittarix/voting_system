package voting.results;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import voting.dto.results.CountyResultData;
import voting.dto.results.vote.VoteData;
import voting.exception.NotFoundException;
import voting.model.Candidate;
import voting.model.County;
import voting.model.District;
import voting.model.Party;
import voting.results.model.result.*;
import voting.results.model.votecount.CandidateVote;
import voting.results.model.votecount.PartyVote;
import voting.results.model.votecount.Vote;
import voting.results.repository.ResultRepository;
import voting.service.CandidateService;
import voting.service.DistrictService;
import voting.service.PartyService;

import java.util.List;
import java.util.stream.Collectors;

import static voting.results.model.result.ResultType.SINGLE_MANDATE;

/**
 * Created by domas on 2/23/17.
 */
@Service
public class ResultServiceImpl implements ResultService {

    private final DistrictService districtService;
    private final PartyService partyService;
    private final CandidateService candidateService;
    private final ResultRepository resultRepository;

    private MultiMandateResultSummary mmSummary;


    @Autowired
    public ResultServiceImpl(DistrictService districtService,
                             PartyService partyService,
                             CandidateService candidateService,
                             ResultRepository resultRepository) {
        this.districtService = districtService;
        this.partyService = partyService;
        this.candidateService = candidateService;
        this.resultRepository = resultRepository;
    }


    @Override
    public CountySMResult getCountySmResult(Long countyId) {
        return resultRepository.findSmResultByCounty(districtService.getCounty(countyId));
    }

    @Override
    public CountyMMResult getCountyMmResult(Long countyId) {
        return resultRepository.findMmResultByCounty(districtService.getCounty(countyId));
    }

    @Override
    public DistrictSMResult getDistrictSmResult(Long districtId) {
        District district = districtService.getDistrict(districtId);
        DistrictSMResult result = resultRepository.findSmResultByDistrict(district);
        if (result == null) {
            result = (DistrictSMResult) saveNewDistrictResult(district, SINGLE_MANDATE);
        }
        return result;
    }

    @Override
    public DistrictMMResult getDistrictMmResult(Long districtId) {
        District district = districtService.getDistrict(districtId);
        DistrictMMResult result = resultRepository.findMmResultByDistrict(district);
        if (result == null) {
            result = (DistrictMMResult) saveNewDistrictResult(district, ResultType.MULTI_MANDATE);
        }
        return result;
    }

    @Transactional
    private List<DistrictMMResult> getAllDistrictMmResults() {
        return districtService.getDistricts().stream()
                .map(d -> getDistrictMmResult(d.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public MultiMandateResultSummary getMmResultSummary() {
        if (mmSummary == null) {
            constructNewMmResultSummary();
        }
        return mmSummary;
    }

    @Transactional
    @Override
    public CountySMResult addCountySmResult(CountyResultData resultDTO) {
        County county = districtService.getCounty(resultDTO.getCountyId());
        if (resultRepository.existsSmResultByCounty(county)) {
            throw new IllegalArgumentException(String.format("Apylinkės \"%s\" rezultatas jau užregistruotas", county));
        }
        county.setSmResult((CountySMResult) convertToCountyResult(resultDTO, SINGLE_MANDATE));
        districtService.save(county.getDistrict());
        return county.getSmResult();
    }

    @Transactional
    @Override
    public CountyMMResult addCountyMmResult(CountyResultData resultDTO) {
        County county = districtService.getCounty(resultDTO.getCountyId());
        if (resultRepository.existsMmResultByCounty(county)) {
            throw new IllegalArgumentException(String.format("Apylinkės \"%s\" rezultatas jau užregistruotas", county));
        }
        county.setMmResult((CountyMMResult) convertToCountyResult(resultDTO, ResultType.MULTI_MANDATE));
        districtService.save(county.getDistrict());
        return county.getMmResult();
    }

    @Transactional
    @Override
    public void confirmCountyResult(Long id) {
        CountyResult result = getCountyResult(id);
        throwIllegalArgumentIfConfirmed(result, "Rezultatas jau patvirtintas");

        result.setConfirmed(true);
        District district = result.getCounty().getDistrict();
        districtService.save(district);

        ResultType type = result instanceof CountySMResult ?
                          SINGLE_MANDATE :
                          ResultType.MULTI_MANDATE;

        updateDistrictResult(district, result, type);
        if (type == ResultType.MULTI_MANDATE) {
            updateMmResultSummary((CountyMMResult) result);
        }
    }

    @Transactional
    private void updateDistrictResult(District district, CountyResult countyResult, ResultType type) {
        DistrictResult districtResult = type == SINGLE_MANDATE ?
                resultRepository.findSmResultByDistrict(district) :
                resultRepository.findMmResultByDistrict(district);
        if (districtResult == null) {
            saveNewDistrictResult(district, type);
        } else {
            districtResult.combineResults(countyResult);
        }
        districtService.save(district);
    }

    @Transactional
    private DistrictResult saveNewDistrictResult(District district, ResultType type) {
        DistrictResult result = constructDistrictResult(district, type);
        district.setResultByType(result, type);
        districtService.save(district);
        return district.getResultByType(type);
    }

    private void updateMmResultSummary(CountyMMResult result) {
        if (mmSummary == null) {
            constructNewMmResultSummary();
        } else {
            mmSummary.combineResults(result);
            mmSummary.setResults(getAllDistrictMmResults());
        }
    }

    private void constructNewMmResultSummary() {
        mmSummary = new MultiMandateResultSummary(partyService.getParties(), getAllDistrictMmResults());
    }

    @Transactional
    @Override
    public void deleteCountyResult(Long id) {
        CountyResult result = getCountyResult(id);
        throwIllegalArgumentIfConfirmed(result, "Rezultatas jau patvritintas, negalima ištrint");

        County county = result.getCounty();
        if (result instanceof CountySMResult) {
            county.setSmResult(null);
        } else {
            county.setMmResult(null);
        }

        districtService.save(county.getDistrict());
    }


    private Result getResult(Long id) {
        Result result = resultRepository.findOne(id);
        if (result == null) {
            throw new NotFoundException("Nepavyko rasti rezultato su id " + id);
        }
        return result;
    }

    private CountyResult getCountyResult(Long id) {
        Result result = getResult(id);
        if (!isCountyResult(result)) {
            throw new NotFoundException("Nepavyko rasti apylinkės rezultato su id " + id);
        }
        return (CountyResult) result;
    }

    private DistrictResult constructDistrictResult(District district, ResultType type) {
        DistrictResult districtResult = constructBlankDistrictResult(district, type);
        district.getCounties().stream()
                .map(c -> c.getResultByType(type))
                .filter(c -> c != null && c.isConfirmed())
                .forEach(districtResult::combineResults);
        return districtResult;
    }

    private DistrictResult constructBlankDistrictResult(District district, ResultType type) {
        DistrictResult result = type == SINGLE_MANDATE ?
                new DistrictSMResult() :
                new DistrictMMResult();
        List<Vote> voteList = type == SINGLE_MANDATE ?
                constructBlankCandidateVoteList(district.getCandidates()):
                constructBlankPartyVoteList(partyService.getParties());
        voteList.forEach(result::addVote);
        return result;
    }

    private List<Vote> constructBlankCandidateVoteList(List<Candidate> candidates) {
        return candidates.stream()
                .map(c -> new CandidateVote(c, 0L))
                .collect(Collectors.toList());
    }

    private List<Vote> constructBlankPartyVoteList(List<Party> parties) {
        return parties.stream()
                .map(p -> new PartyVote(p, 0L))
                .collect(Collectors.toList());
    }

    private void throwIllegalArgumentIfConfirmed(CountyResult cr, String message) {
        if (cr.isConfirmed()) {
            throw new IllegalArgumentException(message);
        }
    }

    private boolean isCountyResult(Result result) {
        return result instanceof CountyResult;
    }

    private CountyResult convertToCountyResult(CountyResultData resultDTO, ResultType type) {
        CountyResult result = type == SINGLE_MANDATE ?
                              new CountySMResult() :
                              new CountyMMResult();

        County county = districtService.getCounty(resultDTO.getCountyId());
        result.setCounty(county);

        List<Vote> voteList = (type == SINGLE_MANDATE) ?
                              convertToCandidateVotes(resultDTO.getVoteList()) :
                              convertToPartyVotes(resultDTO.getVoteList());

        voteList.forEach(result::addVote);

        result.setConfirmed(false);
        result.setSpoiledBallots(resultDTO.getSpoiledBallots());
        return result;
    }

    private List<Vote> convertToCandidateVotes(List<VoteData> votes) {
        return votes.stream()
                    .map(v -> new CandidateVote(candidateService.getCandidate(v.getUnitId()), v.getVotes()))
                    .collect(Collectors.toList());
    }

    private List<Vote> convertToPartyVotes(List<VoteData> votes) {
        return votes.stream()
                .map(v -> new PartyVote(partyService.getParty(v.getUnitId()), v.getVotes()))
                .collect(Collectors.toList());
    }
}