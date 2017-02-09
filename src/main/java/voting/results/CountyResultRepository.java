package voting.results;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

/**
 * Created by andrius on 1/24/17.
 */

@Repository
public class CountyResultRepository {

    @Autowired
    private EntityManager em;

    public CountyResult save(CountyResult countyResult) {
        countyResult.setCreatedOn(new Date());
        em.persist(countyResult);
        return countyResult;
    }

    public CountyResult findOne(Long id) {
        return (CountyResult) em.createQuery("SELECT cr FROM CountyResult cr WHERE cr.id = :id")
                 .setParameter("id", id)
                 .getSingleResult();
    }

    public boolean exists(Long id) {
        return findOne(id) != null ? true : false;
    }

    public List<CountyResult> findAll() {
        return em.createQuery("SELECT cr FROM COuntyResult cr").getResultList();
    }

    public long count() {
        return findAll().size();
    }

    public int delete(Long id) {
        return em.createQuery("DELETE FROM CountyResult cr WHERE cr.id = :id")
                 .setParameter("id", id)
                 .executeUpdate();
    }

    public void delete(CountyResult countyResult) {
        em.remove(countyResult);
    }

    public void delete(List<CountyResult> list) {
        list.forEach(cr -> delete(cr.getId()));
    }

    public int deleteAll() {
        return em.createQuery("DELETE FROM CountyResult cr").executeUpdate();
    }
}