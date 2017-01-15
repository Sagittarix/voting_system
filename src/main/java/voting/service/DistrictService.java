package voting.service;

import voting.model.District;
import voting.model.DistrictCandidatesData;
import voting.model.DistrictData;

import java.util.List;

/**
 * Created by domas on 1/10/17.
 */
public interface DistrictService {

    District addNewDistrict(DistrictData districtData);

//    District updateDistrict(DistrictData districtData);

    void deleteDistrict(Long id);

    District getDistrict(Long id);

    List<District> getDistricts();

    District addCandidateList(DistrictCandidatesData districtCandidatesData);
}
