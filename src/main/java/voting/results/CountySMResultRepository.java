package voting.results;

import org.springframework.data.repository.CrudRepository;
import voting.model.County;
import voting.results.model.result.CountySMResult;

/**
 * Created by domas on 2/27/17.
 */
public interface CountySMResultRepository extends CrudRepository<CountySMResult, Long> {

    public CountySMResult findOneByCounty(County county);
}
