package voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import voting.dto.CandidateData;
import voting.dto.DistrictData;
import voting.exception.NotFoundException;
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
        checkExistance(id);
        District district = districtRepository.findOne(id);
        if (district != null) {
            district.removeAllCandidates();
            districtRepository.delete(id);
        }
    }

    @Override
    public District getDistrict(Long id) {
        checkExistance(id);
        return districtRepository.findOne(id);
    }

    @Override
    public List<District> getDistricts() {
        return (List<District>) districtRepository.findAll();
    }

    @Transactional
    @Override
    public District setCandidateList(Long id, List<CandidateData> candidateListData) {
        checkExistance(id);
        District district = districtRepository.findOne(id);

        district.removeAllCandidates();
        candidateListData.forEach(
                candidateData -> {
                    Candidate candidate = candidateService.getCandidate(candidateData.getPersonId());
                    if (candidate == null) {
                        candidate = candidateService.addNewCandidate(candidateData);
                    } else if (candidate.getDistrict() != null && !candidate.getDistrict().equals(district)) {
                        throw (new IllegalArgumentException(String.format("%s %s, pid %s is already bound to other district (id:%d, name: %s)",
                                candidate.getFirstName(), candidate.getLastName(), candidate.getPersonId(),
                                candidate.getDistrict().getId(), candidate.getDistrict().getName())));
                    }
                    district.addCandidate(candidate);
                });
        return districtRepository.save(district);
    }

    @Override
    public void deleteCandidateList(Long id) {
        checkExistance(id);
        District district = districtRepository.findOne(id);
        district.removeAllCandidates();
        districtRepository.save(district);
    }

    public void checkExistance(Long id) {
        if (!districtRepository.exists(id)) {
            throw (new NotFoundException("district with id " + id));
        };
    }

}
