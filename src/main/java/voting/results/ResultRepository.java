package voting.results;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import voting.model.County;
import voting.results.model.result.Result;

import java.util.List;

/**
 * Created by domas on 2/23/17.
 */

public interface ResultRepository extends CrudRepository<Result, Long> {

//    @Query("SELECT r FROM Result r WHERE type = ?1")
//    List<Result> findByType(String type);
//
//    @Query("SELECT r FROM Result r WHERE county = ?1 AND type = ?2")
//    List<Result> findOneByCountyAndType(County county, String type);
}