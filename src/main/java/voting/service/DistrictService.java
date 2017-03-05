package voting.service;

import com.opencsv.exceptions.CsvException;
import org.springframework.web.multipart.MultipartFile;
import voting.dto.CountyData;
import voting.dto.CountyRepresentation;
import voting.dto.DistrictData;
import voting.model.Candidate;
import voting.model.County;
import voting.model.District;

import java.io.IOException;
import java.util.List;

/**
 * Created by domas on 1/10/17.
 */
public interface DistrictService {

    District addNewDistrict(DistrictData districtData);

    void deleteDistrict(Long id);

    District getDistrict(Long id);

    District getDistrict(String name);

    List<District> getDistricts();

    List<Candidate> getCandidateList(Long id);

    District setCandidateList(Long id, MultipartFile file) throws IOException, CsvException;

    void deleteCandidateList(Long id);

    boolean exists(Long id);

    boolean exists(String name);

    County getCounty(Long id);

    District addCounty(Long districtId, CountyData countyData);

    CountyRepresentation updateCounty(Long districtId, CountyData countyData, Long countyId);

    void deleteCounty(Long countyId);

    District updateDistrict(DistrictData districtData, Long id);
}
