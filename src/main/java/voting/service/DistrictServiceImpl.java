package voting.service;

import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import voting.dto.CandidateData;
import voting.dto.DistrictData;
import voting.exception.NotFoundException;
import voting.model.Candidate;
import voting.model.County;
import voting.model.District;
import voting.repository.DistrictRepository;

import java.io.IOException;
import java.util.List;

/**
 * Created by domas on 1/10/17.
 */
@Service
public class DistrictServiceImpl implements DistrictService {

    private DistrictRepository districtRepository;
    private CandidateService candidateService;
    private StorageService storageService;
    private ParsingService parsingService;

    @Autowired
    public DistrictServiceImpl(DistrictRepository districtRepository, CandidateService candidateService, StorageService storageService, ParsingService parsingService) {
        this.districtRepository = districtRepository;
        this.candidateService = candidateService;
        this.storageService = storageService;
        this.parsingService = parsingService;
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
        District district = getDistrict(id);
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
    public District setCandidateList(Long id, MultipartFile file) throws IOException, CsvException {
        District district = getDistrict(id);
        if (district == null) {
            throw (new NotFoundException("Couldn't find district with id " + id));
        }

        String fileName = String.format("district_%d.csv", id);
        storageService.store(fileName, file);
        Resource fileResource = storageService.loadAsResource(fileName);
        List<CandidateData> candidateListData = (parsingService.parseSingleMandateCandidateList(fileResource.getFile()));

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
        District district = getDistrict(id);
        if (district != null) {
            district.removeAllCandidates();
            districtRepository.save(district);
        }
    }

}
