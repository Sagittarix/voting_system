package voting.results;

import org.springframework.stereotype.Service;
import voting.model.County;
import voting.model.results.CountyResult;

/**
 * Created by andrius on 2/18/17.
 *
 * INITIAL RESULTS PROCESSING SERVICE
 */

@Service
public class ElectionResultsProcessingService {

    // get total votes count for particular CountyResult
    public Long getTotalVotesCountFromCountyResult(CountyResult cr) {
        return cr.getUnitVotesList()
                 .stream()
                 .mapToLong(cv -> cv.getVotes())
                 .sum();
    }

    // get single-mandate result from particular county
    // NoSuchElementException not caught
    public CountyResult getSingleMandateResultFromCounty(County county) {
        return county.getCountyResultList()
                     .stream()
                     .filter(CountyResult::isSingleMandateSystem)
                     .findFirst()
                     .get();
    }

    // get single-mandate result from particular county
    // NoSuchElementException not caught
    public CountyResult getMultiMandateResultFromCounty(County county) {
        return county.getCountyResultList()
                     .stream()
                     .filter(cr -> !cr.isSingleMandateSystem())
                     .findFirst()
                     .get();
    }

}
