package voting.results.model.result;

import voting.model.District;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * Created by domas on 2/22/17.
 */
@Entity
public abstract class DistrictResult extends Result {

    @OneToOne
    @JoinColumn(name = "district_id")
    private District district;

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }
}
