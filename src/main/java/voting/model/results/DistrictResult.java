package voting.model.results;

import voting.model.District;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class DistrictResult extends Result {


    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = {}
    )
    @JoinColumn(name = "district_id", nullable = false)
    private District district;



    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }
}
