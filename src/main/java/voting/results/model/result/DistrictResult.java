package voting.results.model.result;

import voting.model.District;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

import static voting.results.model.result.ResultType.*;
import static voting.results.model.result.ResultType.SINGLE_MANDATE;

/**
 * Created by domas on 2/22/17.
 */
@Entity
public abstract class DistrictResult extends Result {

    @OneToOne
    @JoinColumn(name = "district_id")
    private District district;

    private int confirmedCountyResults;
    private int totalCountyResults;

    @OneToMany
    List<CountyResult> countyResults = new ArrayList<>();

    public DistrictResult() {
    }

    public DistrictResult(District district) {
        super();
        this.district = district;
        countyResults = new ArrayList<>();
        ResultType type = (this instanceof DistrictSMResult) ?
                SINGLE_MANDATE :
                MULTI_MANDATE;
        district.setResultByType(this, type);
        this.totalCountyResults = district.getCounties().size();
        this.confirmedCountyResults = 0;
    }


    public void addCountyResult(CountyResult cr) {
        countyResults.add(cr);
        confirmedCountyResults++;
        super.combineResults(cr);
    }

    public District getDistrict() {
        return district;
    }

    public int getConfirmedCountyResults() {
        return confirmedCountyResults;
    }

    public int getTotalCountyResults() {
        return totalCountyResults;
    }

    public List<CountyResult> getCountyResults() {
        return countyResults;
    }
}
