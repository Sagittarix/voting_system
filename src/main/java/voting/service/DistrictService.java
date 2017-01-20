package voting.service;

import voting.dto.CandidateData;
import voting.dto.DistrictData;
import voting.model.District;

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

    District addCandidateList(Long id, List<CandidateData> candidateListData);

    void deleteCandidateList(Long id);
}
