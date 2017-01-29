package voting.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import voting.model.County;
import javax.persistence.EntityManager;

/**
 * Created by andrius on 1/21/17.
 */

@Repository
public class CountyRepository {

    @Autowired
    private EntityManager em;

    public County save(County county) {
        em.persist(county);
        return county;
    }
}
