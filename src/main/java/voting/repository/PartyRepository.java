package voting.repository;

import org.springframework.data.repository.CrudRepository;
import voting.model.Party;

/**
 * Created by domas on 1/10/17.
 */
public interface PartyRepository extends CrudRepository<Party, Long> {

    public Party findByName(String name);
}
