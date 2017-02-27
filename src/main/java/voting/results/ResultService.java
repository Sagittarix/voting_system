package voting.results;


import voting.results.model.result.CountyMMResult;
import voting.results.model.result.CountySMResult;

import java.util.List;

/**
 * Created by domas on 2/22/17.
 */
public interface ResultService {

    public CountySMResult addCountySMResult(CountySMResult result);

    public CountyMMResult addCountyMMResult(CountyMMResult result);

    public CountySMResult getCountySMResult(Long countyId);

//    public CountyMMResult getCountyMMResult(Long countyId);
//
//    List<CountySMResult> getAllCountySMResults();
//
    public void confirmResult(Long id);

    public void deleteResult(Long id);


}
