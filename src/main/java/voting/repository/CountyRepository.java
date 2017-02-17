package voting.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import voting.model.County;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * Created by andrius on 1/21/17.
 */

@Repository
public interface CountyRepository extends CrudRepository<County, Long> { }
