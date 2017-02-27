package voting.results;

import org.springframework.data.repository.CrudRepository;
import voting.results.model.result.DistrictMMResult;

/**
 * Created by domas on 2/27/17.
 */
public interface DistrictMMResultRepository extends CrudRepository<DistrictMMResult, Long> {
}
