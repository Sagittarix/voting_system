package voting.service;

import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.nio.file.Path;
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
    public DistrictServiceImpl(DistrictRepository districtRepository,
                               CandidateService candidateService,
                               StorageService storageService,
                               ParsingService parsingService) {
        this.districtRepository = districtRepository;
        this.candidateService = candidateService;
        this.storageService = storageService;
        this.parsingService = parsingService;
    }

    @Override
    public District getDistrict(Long id) {
        District district = districtRepository.findOne(id);
        if (district == null) {
            throw (new NotFoundException("Couldn't find district with id " + id));
        }
        return district;
    }

    @Override
    public District getDistrict(String name) {
        District district = districtRepository.findByName(name);
        if (district == null) {
            throw (new NotFoundException("Couldn't find district with name " + name));
        }
        return district;
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
        district.removeAllCandidates();
        districtRepository.delete(id);
    }

    @Override
    public List<District> getDistricts() {
        return (List<District>) districtRepository.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public District setCandidateList(Long id, MultipartFile file) throws IOException, CsvException {
        return saveCandidateList(getDistrict(id), file);
    }

    @Override
    public void deleteCandidateList(Long id) {
        District district = getDistrict(id);
        district.removeAllCandidates();
        districtRepository.save(district);
    }

    @Override
    public boolean exists(Long id) {
        return districtRepository.exists(id);
    }

    @Override
    public boolean exists(String name) {
        return districtRepository.existsByName(name);
    }


    @Transactional(rollbackFor = Exception.class)
    private District saveCandidateList(District district, MultipartFile file) throws IOException, CsvException {

        List<CandidateData> candidateListData = extractCandidateList(file);
        district.removeAllCandidates();

        candidateListData.forEach(
                candidateData -> {
                    candidateData.setDistrictId(district.getId());
                    candidateData.setDistrictName(district.getName());
                    Candidate candidate = candidateService.addNewOrGetIfExists(candidateData);
                    district.addCandidate(candidate);
                });
        districtRepository.save(district);

        String fileName = String.format("district_%d.csv", district.getId());
        storageService.store(fileName, file);

        return district;
    }

    private List<CandidateData> extractCandidateList(MultipartFile file) throws IOException, CsvException {
        Path tempFile = storageService.storeTemporary(file);

        List<CandidateData> candidateListData;
        try {
            candidateListData = parsingService.parseSingleMandateCandidateList(tempFile.toFile());
        } finally {
            storageService.delete(tempFile);
        }
        return candidateListData;
    }

}
