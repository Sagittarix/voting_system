package voting.repository;

import org.springframework.data.repository.CrudRepository;
import voting.model.Candidate;

/**
 * Created by domas on 1/12/17.
 */
public interface CandidateRepository extends CrudRepository<Candidate, Long> {
    public Candidate findByPersonId(String personId);
}
