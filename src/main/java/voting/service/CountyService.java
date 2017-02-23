package voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import voting.dto.CandidateRepresentation;
import voting.dto.CountyData;
import voting.dto.CountyRepresentation;
import voting.dto.DistrictRepresentation;
import voting.model.CountyRep;
import voting.model.District;
import voting.repository.CountyRepRepository;
import voting.repository.CountyRepository;
import voting.repository.DistrictRepository;
import voting.model.County;

import java.util.List;

/**
 * Created by andrius on 1/21/17.
 */

@Service
public class CountyService {

    private final CountyRepository countyRepository;
    private final DistrictRepository districtRepository;
    private final CountyRepRepository countyRepRepository;

    @Autowired
    public CountyService(CountyRepository countyRepository,
                         DistrictRepository districtRepository,
                         CountyRepRepository countyRepRepository) {
        this.countyRepository = countyRepository;
        this.districtRepository = districtRepository;
        this.countyRepRepository = countyRepRepository;
    }

    public County findOne(Long id) {
        return countyRepository.findOne(id);
    }

    @Transactional
    public CountyRepresentation saveWithDistrict(CountyData countyData) {
        County county = mapDataToEntity(countyData);
        return new CountyRepresentation(countyRepository.save(county));
    }

    private County mapDataToEntity(CountyData countyData) {
        County county = new County();
        county.setName(countyData.getName());
        county.setVoterCount(countyData.getVoterCount());
        county.setAddress(countyData.getAddress());
        county.setDistrict(districtRepository.findOne(countyData.getDistrictId()));
        return county;
    }

    @Transactional
    public County saveStandalone(CountyData countyData) {
        County county = new County();
        county.setName(countyData.getName());
        county.setVoterCount(countyData.getVoterCount());
        return countyRepository.save(county);
    }

    public List<CandidateRepresentation> getAllSingleMandateCandidatesForCounty(Long id) {
        District district = countyRepository.findOne(id).getDistrict();
        return new DistrictRepresentation(district).getCandidates();
    }

    @Transactional
    public void delete(Long id) {
        County county = countyRepository.findOne(id);
        CountyRep cr = county.getCountyRep();
        if (cr != null) countyRepRepository.delete(cr);
        countyRepository.delete(county);
    }
}
