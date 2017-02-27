package voting.results;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import voting.model.County;
import voting.results.model.result.CountyMMResult;
import voting.results.model.result.CountySMResult;
import voting.results.model.result.Result;
import voting.service.DistrictService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by domas on 2/23/17.
 */
@Service
public class ResultServiceImpl implements ResultService {

    private final DistrictService districtService;
    private final ResultRepository resultRepository;
    private final CountySMResultRepository countySMrepo;
    private final CountyMMResultRepository countyMMrepo;
    private final DistrictSMResultRepository districtSMrepo;
    private final DistrictMMResultRepository districtMMrepo;

    @Autowired
    public ResultServiceImpl(DistrictService districtService,
                             ResultRepository resultRepository,
                             CountySMResultRepository countySMrepo,
                             CountyMMResultRepository countyMMrepo,
                             DistrictSMResultRepository districtSMrepo,
                             DistrictMMResultRepository districtMMrepo) {
        this.districtService = districtService;
        this.resultRepository = resultRepository;
        this.countySMrepo = countySMrepo;
        this.countyMMrepo = countyMMrepo;
        this.districtSMrepo = districtSMrepo;
        this.districtMMrepo = districtMMrepo;
    }


    @Override
    public CountySMResult addCountySMResult(CountySMResult result) {
        return resultRepository.save(result);
    }

    @Override
    public CountyMMResult addCountyMMResult(CountyMMResult result) {
        return resultRepository.save(result);
    }

    @Override
    public CountySMResult getCountySMResult(Long countyId) {
        County county = districtService.getCounty(countyId);
        CountySMResult result = countySMrepo.findOneByCounty(county);
        System.out.println(result);
        return result;
//        resultRepository.findOne(countyId);
//        if (result instanceof CountySMResult) {
//            return (CountySMResult) resultRepository.findOne(countyId);
//        }
//        else {
//            throw new RuntimeException();
//        }
    }

//    @Override
//    public CountyMMResult getCountyMMResult(Long id) {
//        return null;
//    }
//
//
//    @Override
//    public List<CountySMResult> getAllCountySMResults() {
////        County county = districtService.getCounty(countyId);
//        List<Result> results = resultRepository.findByType("county_sm");
//
////        Result result = resultRepository.findOne(countyId);
////        if (result instanceof CountySMResult) {
////            return (CountySMResult) resultRepository.findOne(countyId);
////        }
////        else {
////            throw new RuntimeException();
////        }
//        return results.stream().map(r -> (CountySMResult) r).collect(Collectors.toList());
//    }


    @Override
    public void confirmResult(Long id) {

    }

    @Override
    public void deleteResult(Long id) {
        resultRepository.delete(id);
    }
}