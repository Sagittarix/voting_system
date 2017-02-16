package voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import voting.dto.CandidateRepresentation;
import voting.dto.CountyData;
import voting.dto.DistrictRepresentation;
import voting.model.District;
import voting.repository.CountyRepository;
import voting.repository.DistrictRepository;
import voting.model.County;

import java.util.List;

/**
 * Created by andrius on 1/21/17.
 */

@Service
public class CountyService {

    @Autowired
    private CountyRepository countyRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Transactional
    public County saveWithDistrict(CountyData countyData) {
        County county = mapDataToEntity(countyData);
        return countyRepository.save(county);
    }

    private County mapDataToEntity(CountyData countyData) {
        County county = new County();
        county.setName(countyData.getName());
        county.setVoterCount(countyData.getVoterCount());
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
}
