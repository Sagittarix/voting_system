package voting.results;

import org.springframework.stereotype.Service;
import voting.model.County;

/**
 * Created by andrius on 2/18/17.
 */

@Service
public class ElectionResultsProcessingService {

    // get total votes count for particular CountyResult
    public Long getTotalVotesCountFromCountyResult(CountyResult cr) {
        return cr.getCandidateVotesList()
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

    public Long getCountyVotersCount(County county) {
        county.get
    }

}
