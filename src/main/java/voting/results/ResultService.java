package voting.results;


import voting.dto.results.CountyResultDTO;
import voting.results.model.result.CountyMMResult;
import voting.results.model.result.CountySMResult;

import java.util.List;

/**
 * Created by domas on 2/22/17.
 */
public interface ResultService {

    public CountySMResult addCountySMResult(CountyResultDTO resultDTO);

    public CountyMMResult addCountyMMResult(CountyResultDTO resultDTO);

    public CountySMResult getCountySMResult(Long countyId);

    public CountyMMResult getCountyMMResult(Long countyId);

    public void confirmCountyResult(Long resultId);

    public void deleteCountyResult(Long resultId);


}
