package voting.results.repository;

import org.springframework.data.repository.CrudRepository;
import voting.results.model.result.Result;

/**
 * Created by domas on 2/23/17.
 */

public interface ResultRepository extends CrudRepository<Result, Long> {

}