package voting.results;

import org.springframework.data.repository.CrudRepository;
import voting.results.model.result.DistrictSMResult;

/**
 * Created by domas on 2/27/17.
 */
public interface DistrictSMResultRepository extends CrudRepository<DistrictSMResult, Long> {
}
