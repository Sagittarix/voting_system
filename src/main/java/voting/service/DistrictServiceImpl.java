package voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import voting.dto.CandidateData;
import voting.dto.DistrictData;
import voting.model.Candidate;
import voting.model.County;
import voting.model.District;
import voting.repository.DistrictRepository;

import java.util.List;

/**
 * Created by domas on 1/10/17.
 */
@Service
public class DistrictServiceImpl implements DistrictService {

    private DistrictRepository districtRepository;
    private CandidateService candidateService;

    @Autowired
    public DistrictServiceImpl(DistrictRepository districtRepository, CandidateService candidateService) {
        this.districtRepository = districtRepository;
        this.candidateService = candidateService;
    }


    @Transactional
    @Override
    public District addNewDistrict(DistrictData districtData) {
        District district = new District(districtData.getName());
        if (districtData.getCountiesData() != null) {
            districtData.getCountiesData().forEach(
                    countyData -> {
                        County county = new County(countyData.getName(), countyData.getVoterCount());
                        district.addCounty(county);
                    });
        }
        return districtRepository.save(district);
    }

    @Transactional
    @Override
    public void deleteDistrict(Long id) {
        District district = districtRepository.findOne(id);
        if (district != null) {
            district.removeAllCandidates();
            districtRepository.delete(id);
        }
    }

    @Override
    public District getDistrict(Long id) {
        return districtRepository.findOne(id);
    }

    @Override
    public List<District> getDistricts() {
        return (List<District>) districtRepository.findAll();
    }

    @Transactional
    @Override
    public District addCandidateList(Long id, List<CandidateData> candidateListData) {
        District district = districtRepository.findOne(id);
        // TODO: add validation: jei kandidatas jau priskirtas kitam districtui, turi mesti errora
        candidateListData.forEach(
                candidateData -> {
                    Candidate candidate = candidateService.getCandidate(candidateData.getPersonId());
                    if (candidate == null) {
                        candidate = candidateService.addNewCandidate(candidateData);
                    }
                    district.addCandidate(candidate);
                });
        return districtRepository.save(district);
    }

    @Override
    public void deleteCandidateList(Long id) {
        District district = districtRepository.findOne(id);
        district.removeAllCandidates();
        districtRepository.save(district);
    }

    @Override
    public boolean districtExists(Long id) {
        return districtRepository.exists(id);
    }

}
