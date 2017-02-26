package voting.repository.results;

import org.springframework.data.repository.CrudRepository;
import voting.model.County;
import voting.model.results.CountyResult;

import java.util.List;

/**
 * Created by domas on 1/12/17.
 */
public interface CountyResultRepository extends CrudRepository<CountyResult, Long> {

    public List<CountyResult> findBySingleMandateSystemTrue();

    public List<CountyResult> findBySingleMandateSystemFalse();

    public List<CountyResult> findByCounty(County county);

    public CountyResult findOneByCountyAndSingleMandateSystemTrue(County county);

    public CountyResult findOneByCountyAndSingleMandateSystemFalse(County county);
}
