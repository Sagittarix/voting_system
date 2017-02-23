package voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import voting.dto.CountyRepresentativeData;
import voting.dto.CountyRepresentativeRepresentation;
import voting.model.CountyRep;
import voting.repository.CountyRepRepository;
import voting.utils.BCryptHelper;

import java.util.List;

/**
 * Created by domas on 1/10/17.
 */

@Service
public class CountyRepServiceImpl implements CountyRepService {

    private CountyRepRepository countyRepRepository;
    private CountyService countyService;

    @Autowired
    public CountyRepServiceImpl(CountyRepRepository countyRepRepository,
                                CountyService countyService) {
        this.countyRepRepository = countyRepRepository;
        this.countyService = countyService;
    }

    /*@Transactional
    @Override
    public CountyRep addNewCountyRep(CountyRep countyRep) {
        return countyRepRepository.save(countyRep);
    }*/

    @Transactional
    @Override
    public CountyRep addNewCountyRep(CountyRepresentativeData countyRepData) {
        CountyRep countyRep = new CountyRep();
        countyRep.setFirstName(countyRepData.getFirstName());
        countyRep.setLastName(countyRepData.getLastName());
        countyRep.setEmail(countyRepData.getEmail());
        countyRep.setPassword_digest(BCryptHelper.generateRandomPassword(9));
        countyRep.setCounty(countyService.findOne(countyRepData.getCountyId()));
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
    public CountyRepresentativeRepresentation getCountyRep(Long id) {
        return new CountyRepresentativeRepresentation(countyRepRepository.findOne(id));
    }
}
