package voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import voting.model.CountyRep;
import voting.repository.CountyRepRepository;

import java.util.List;

/**
 * Created by domas on 1/10/17.
 */

@Service
public class CountyRepServiceImpl implements CountyRepService {

    private CountyRepRepository countyRepRepository;

    @Autowired
    public CountyRepServiceImpl(CountyRepRepository countyRepRepository) {
        this.countyRepRepository = countyRepRepository;
    }

    @Transactional
    @Override
    public CountyRep addNewCountyRep(CountyRep countyRep) {
        return countyRepRepository.save(countyRep);
    }

    @Transactional
    @Override
    public void deleteCountyRep(Long id) {
        countyRepRepository.delete(id);
    }

    @Override
    public List<CountyRep> getCountyReps() {
        return (List<CountyRep>) countyRepRepository.findAll();
    }

    @Override
    public CountyRep getCountyRep(Long id) {
        return countyRepRepository.findOne(id);
    }
}
