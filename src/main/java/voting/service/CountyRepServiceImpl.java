package voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import voting.dto.CountyRepresentativeData;
import voting.exception.NotFoundException;
import voting.model.County;
import voting.model.CountyRep;
import voting.repository.CountyRepRepository;
import voting.utils.BCryptUtils;

import java.util.List;

/**
 * Created by domas on 1/10/17.
 */

@Service
public class CountyRepServiceImpl implements CountyRepService {

    private CountyRepRepository countyRepRepository;
    private DistrictService districtService;

    @Autowired
    public CountyRepServiceImpl(CountyRepRepository countyRepRepository,
                                DistrictService districtService) {
        this.countyRepRepository = countyRepRepository;
        this.districtService = districtService;
    }

    @Transactional
    @Override
    public CountyRep addNewCountyRep(CountyRepresentativeData countyRepData) {
        County county = districtService.getCounty(countyRepData.getCountyId());
        CountyRep countyRep = new CountyRep();
        //TODO: JavaMail password
        String randomPassword = BCryptUtils.generateRandomPassword(9);

        countyRep.setFirstName(countyRepData.getFirstName());
        countyRep.setLastName(countyRepData.getLastName());
        countyRep.setEmail(countyRepData.getEmail());
        countyRep.setPassword_digest(randomPassword);
        countyRep.setCounty(county);
        countyRep.setRoles(new String[]{"ROLE_REPRESENTATIVE"});
        return countyRepRepository.save(countyRep);
    }

    @Transactional
    @Override
    public void deleteCountyRep(Long id) {
        throwNotFoundIfDoesntExist(id, "Nepavyko rasti apylinkės atstovo su id " + id);
        countyRepRepository.delete(id);
    }

    @Override
    public List<CountyRep> getCountyReps() {
        return (List<CountyRep>) countyRepRepository.findAll();
    }

    @Override
    public CountyRep getCountyRepByUsername(String username) {
        return countyRepRepository.findOneByUsername(username);
    }

    @Override
    public CountyRep getCountyRep(Long id) {
        CountyRep cr = countyRepRepository.findOne(id);
        throwNotFoundIfNull(cr, "Nepavyko rasti apylinkės atstovo su id " + id);
        return cr;
    }

    private void throwNotFoundIfNull(Object obj, String message) {
        if (obj == null) {
            throw new NotFoundException(message);
        }
    }

    private void throwNotFoundIfDoesntExist(Long id, String message) {
        if (!countyRepRepository.exists(id)) {
            throw new NotFoundException(message);
        }
    }

}
