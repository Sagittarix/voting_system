package voting.results;


import voting.dto.results.CountyResultData;
import voting.results.model.result.*;

/**
 * Created by domas on 2/22/17.
 */
public interface ResultService {

    public CountySMResult addCountySmResult(CountyResultData resultDTO);

    public CountyMMResult addCountyMmResult(CountyResultData resultDTO);

    public CountySMResult getCountySmResult(Long countyId);

    public CountyMMResult getCountyMmResult(Long countyId);

    DistrictSMResult getDistrictSmResult(Long districtId);

    DistrictMMResult getDistrictMmResult(Long districtId);

    public void confirmCountyResult(Long resultId);

    public void deleteCountyResult(Long resultId);

    MultiMandateResultSummary getMmResultSummary();
}
