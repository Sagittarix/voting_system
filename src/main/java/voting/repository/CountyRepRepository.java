package voting.repository;

import org.springframework.data.repository.CrudRepository;
import voting.model.CountyRep;

import java.util.Collection;

/**
 * Created by domas on 1/10/17.
 */
public interface CountyRepRepository extends CrudRepository<CountyRep, Long> {

    Collection<CountyRep> findAll();
}
