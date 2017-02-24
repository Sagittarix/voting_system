package voting.service;

import com.opencsv.exceptions.CsvException;
import org.springframework.web.multipart.MultipartFile;
import voting.dto.DistrictData;
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

    District setCandidateList(Long id, MultipartFile file) throws IOException, CsvException;

    void deleteCandidateList(Long id);

    boolean exists(Long id);

    boolean exists(String name);

    County getCounty(Long id);

}
