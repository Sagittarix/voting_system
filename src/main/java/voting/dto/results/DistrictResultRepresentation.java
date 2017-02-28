package voting.dto.results;

import voting.dto.DistrictShortRepresentation;
import voting.results.model.result.DistrictResult;

/**
 * Created by domas on 2/27/17.
 */
public class DistrictResultRepresentation extends ResultRepresentation {

    private DistrictShortRepresentation district;


    public DistrictResultRepresentation() {
    }

    public DistrictResultRepresentation(DistrictResult result) {
        super(result);
        this.district = result.getDistrict() == null ? null : new DistrictShortRepresentation(result.getDistrict());
    }

    public DistrictShortRepresentation getDistrict() {
        return district;
    }

    public void setDistrict(DistrictShortRepresentation district) {
        this.district = district;
    }
}
