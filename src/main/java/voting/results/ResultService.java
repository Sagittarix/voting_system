package voting.results;


import voting.dto.results.CountyResultData;
import voting.results.model.result.CountyMMResult;
import voting.results.model.result.CountySMResult;

/**
 * Created by domas on 2/22/17.
 */
public interface ResultService {

    public CountySMResult addCountySMResult(CountyResultData resultDTO);

    public CountyMMResult addCountyMMResult(CountyResultData resultDTO);

    public CountySMResult getCountySMResult(Long countyId);

    public CountyMMResult getCountyMMResult(Long countyId);

    public void confirmCountyResult(Long resultId);

    public void deleteCountyResult(Long resultId);


}
