package voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import voting.model.Candidate;
import voting.model.County;
import voting.model.District;
import voting.model.DistrictData;
import voting.repository.CandidateRepository;
import voting.repository.DistrictRepository;
import voting.repository.PartyRepository;
import java.util.List;

/**
 * Created by domas on 1/10/17.
 */
@Service
public class DistrictServiceImpl implements DistrictService {

    private DistrictRepository districtRepository;
    private PartyRepository partyRepository;
    private CandidateRepository candidateRepository;

    @Autowired
    public DistrictServiceImpl(DistrictRepository districtRepository, PartyRepository partyRepository, CandidateRepository candidateRepository) {
        this.districtRepository = districtRepository;
        this.partyRepository = partyRepository;
        this.candidateRepository = candidateRepository;
    }

    @Transactional
    @Override
    public District addNewDistrict(DistrictData districtData) {
        District district = new District(districtData.getName(), null, null);
        districtData.getCountiesData().forEach(
                countyData -> {
                    County county = new County(countyData.getName(), null, countyData.getVoterCount());
                    district.addCounty(county);
                });
        districtData.getCandidatesData().forEach(
                candidateData -> {
                    Candidate candidate = candidateRepository.findByPersonId(candidateData.getPersonId());
                    if (candidate != null) {
                        // TODO: validate that data in both instances are equivalent
                    } else {
                        candidate = new Candidate(candidateData.getPersonId(),
                                candidateData.getFirstName(), candidateData.getLastName(), null, null);
                    }
                    district.addCandidate(candidate);
                });
        return districtRepository.save(district);
    }

    @Transactional
    @Override
    public District updateDistrict(DistrictData districtData) {
        return null;
    }

    @Transactional
    @Override
    public void deleteDistrict(Long id) {
        districtRepository.delete(id);
    }

    @Override
    public District getDistrict(Long id) {
        return districtRepository.findOne(id);
    }

    @Override
    public List<District> getDistricts() {
        return (List<District>) districtRepository.findAll();
    }
}
