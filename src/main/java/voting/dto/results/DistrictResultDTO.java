package voting.dto.results;

import voting.dto.district.DistrictShortDTO;
import voting.results.model.result.DistrictResult;

/**
 * Created by domas on 2/27/17.
 */
public class DistrictResultDTO extends ResultDTO {

    private DistrictShortDTO district;


    public DistrictResultDTO(DistrictResult result) {
        super(result);
        this.district = result.getDistrict() == null ? null : new DistrictShortDTO(result.getDistrict());
    }

    public DistrictShortDTO getDistrict() {
        return district;
    }
}
