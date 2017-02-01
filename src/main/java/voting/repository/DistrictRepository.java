package voting.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import voting.model.District;

/**
 * Created by domas on 1/10/17.
 */

public interface DistrictRepository extends CrudRepository<District, Long>{

    public District findByName(String name);

}
