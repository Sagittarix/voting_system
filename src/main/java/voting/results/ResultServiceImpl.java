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
import voting.results.repository.ResultRepository;
import voting.service.CandidateService;
import voting.service.DistrictService;
import voting.service.PartyService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by domas on 2/23/17.
 */
@Service
public class ResultServiceImpl implements ResultService {

    private final DistrictService districtService;
    private final PartyService partyService;
    private final CandidateService candidateService;
    private final ResultRepository resultRepository;


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
    public CountySMResult addCountySmResult(CountyResultData resultDTO) {
        County county = districtService.getCounty(resultDTO.getCountyId());
        if (resultRepository.existsSmResultByCounty(county)) {
            throw new IllegalArgumentException(String.format("Apylinkės \"%s\" rezultatas jau užregistruotas", county));
        }
        CountySMResult result = convertToCountySMResult(resultDTO);
        return resultRepository.save(result);
    }

    @Override
    public CountyMMResult addCountyMmResult(CountyResultData resultDTO) {
        County county = districtService.getCounty(resultDTO.getCountyId());
        if (resultRepository.existsMmResultByCounty(county)) {
            throw new IllegalArgumentException(String.format("Apylinkės \"%s\" rezultatas jau užregistruotas", county));
        }
        CountyMMResult result = convertToCountyMMResult(resultDTO);
        return resultRepository.save(result);
    }


    @Override
    public CountySMResult getCountySmResult(Long countyId) {
        County county = districtService.getCounty(countyId);
        CountySMResult result = resultRepository.findSmResultByCounty(county);
        return result;
    }


    @Override
    public CountyMMResult getCountyMmResult(Long countyId) {
        County county = districtService.getCounty(countyId);
        CountyMMResult result = resultRepository.findMmResultByCounty(county);
        return result;
    }

    @Override
    public DistrictSMResult getDistrictSmResult(Long districtId) {
        District district = districtService.getDistrict(districtId);
        DistrictSMResult result = resultRepository.findSmResultByDistrict(district);
        return result;
    }


    @Override
    public DistrictMMResult getDistrictMmResult(Long districtId) {
        District district = districtService.getDistrict(districtId);
        DistrictMMResult result = resultRepository.findMmResultByDistrict(district);
        return result;
    }


    @Transactional
    @Override
    public void confirmCountyResult(Long id) {
        Result result = getResult(id);
        throwInvalidArgumentIfNotCountyResult(result, "Netinkamas rezultato tipas");
        CountyResult cr = (CountyResult) result;
        cr.setConfirmed(true);

        County county = cr.getCounty();
        if (result instanceof CountySMResult) {
//            updateDistrictSmResult(county.getDistrict());
        } else {
//            updateDistrictMmResult(county.getDistrict());
        }

        districtService.save(cr.getCounty().getDistrict());

        // TODO: update district result on confirm
    }

    @Transactional
    @Override
    public void deleteCountyResult(Long id) {
        Result result = getResult(id);
        throwInvalidArgumentIfNotCountyResult(result, "Netinkamas rezultato tipas");

        CountyResult cr = (CountyResult) result;
        County county = cr.getCounty();

        if (result instanceof CountySMResult) {
            county.setSmResult(null);
        } else {
            county.setMmResult(null);
        }

        districtService.save(county.getDistrict());

        // TODO: update district result on confirm

    }



    private Result getResult(Long id) {
        Result result = resultRepository.findOne(id);
        if (result == null) {
            throw new NotFoundException("Nepavyko rasti rezultato su id " + id);
        }
        return result;
    }

    private void throwInvalidArgumentIfNotCountyResult(Result result, String message) {
        if (!isCountyResult(result)) {
            throw new IllegalArgumentException(message);
        }
    }

    private boolean isCountyResult(Result result) {
        return result instanceof CountyResult;
    }


    private CountySMResult convertToCountySMResult(CountyResultData resultDTO) {
        CountySMResult result = new CountySMResult();
        County county = districtService.getCounty(resultDTO.getCountyId());
        result.setCounty(county);

        List<CandidateVote> voteList = resultDTO.getVoteList()
                                                .stream()
                                                .map(v -> convertToCandidateVote(v))
                                                .collect(Collectors.toList());
        voteList.stream().forEach(result::addVote);

        result.setConfirmed(false);
        result.setSpoiledBallots(resultDTO.getSpoiledBallots());
        return result;
    }

    private CountyMMResult convertToCountyMMResult(CountyResultData resultDTO) {
        CountyMMResult result = new CountyMMResult();
        County county = districtService.getCounty(resultDTO.getCountyId());
        result.setCounty(county);

        List<PartyVote> voteList = resultDTO.getVoteList()
                .stream()
                .map(v -> convertToPartyVote(v))
                .collect(Collectors.toList());
        voteList.stream().forEach(result::addVote);

        result.setConfirmed(false);
        result.setSpoiledBallots(resultDTO.getSpoiledBallots());
        return result;
    }

    private CandidateVote convertToCandidateVote(VoteData voteData) {
        Candidate candidate = candidateService.getCandidate(voteData.getUnitId());
        CandidateVote vote = new CandidateVote(candidate, voteData.getVotes());
        return vote;
    }

    private PartyVote convertToPartyVote(VoteData voteData) {
        Party party = partyService.getParty(voteData.getUnitId());
        PartyVote vote = new PartyVote(party, voteData.getVotes());
        return vote;
    }

}