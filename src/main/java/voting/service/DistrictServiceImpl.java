package voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import voting.dto.DistrictCandidatesData;
import voting.dto.DistrictData;
import voting.model.*;
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
    public District addCandidateList(DistrictCandidatesData districtCandidatesData) {
        District district = districtRepository.findOne(districtCandidatesData.getDistrictId());
        // TODO: add validation: jei kandidatas jau priskirtas kitam districtui, turi mesti errora
        // TODO: add validation: kad nebutu kandidatas iskeltas partijos, o partijos sarase jo nera
        districtCandidatesData.getCandidatesData().forEach(
                candidateData -> {
                    Candidate candidate = candidateService.getCandidate(candidateData.getPersonId());
                    if (candidate == null) {
                        candidate = candidateService.addNewCandidate(candidateData);
                    }
                    district.addCandidate(candidate);
                });
        return districtRepository.save(district);
    }

}
