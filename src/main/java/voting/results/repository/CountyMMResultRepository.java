package voting.results.repository;

import org.springframework.data.repository.CrudRepository;
import voting.results.model.result.CountyMMResult;

/**
 * Created by domas on 2/27/17.
 */
public interface CountyMMResultRepository extends CrudRepository<CountyMMResult, Long> {
}
