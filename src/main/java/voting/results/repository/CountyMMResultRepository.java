package voting.results.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import voting.model.County;
import voting.results.model.result.CountyMMResult;

/**
 * Created by domas on 2/27/17.
 */
public interface CountyMMResultRepository extends CrudRepository<CountyMMResult, Long> {

    public CountyMMResult findOneByCounty(County county);

    @Query("select count(r)>0 from CountyMMResult r where r.county = ?1")
    boolean existsByCounty(County county);
}
