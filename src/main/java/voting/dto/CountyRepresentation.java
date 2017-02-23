package voting.dto;

import voting.utils.RepresentationFactory;
import voting.model.County;
import voting.dto.results.CountyResultRepresentation;
import voting.model.results.CountyResult;

import java.util.List;
import java.util.Objects;

/**
 * Created by domas on 1/12/17.
 */
public class CountyRepresentation {

    private Long id;
    private String name;
    private Long voterCount;
    private String address;
    private Long districtId;
    private String districtName;
    private List<CountyResultRepresentation> countyResults;

    public CountyRepresentation() { }

    public CountyRepresentation(County county) {
        this.id = county.getId();
        this.name = county.getName();
        this.voterCount = county.getVoterCount();
        this.address = county.getAddress();
        this.districtId = county.getDistrict().getId();
        this.districtName = county.getDistrict().getName();
        List<CountyResult> results = county.getCountyResultList();
        this.countyResults = (results != null) ?
                RepresentationFactory.makeRepresentationOfCountyResultsList(results) : null;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getVoterCount() {
        return voterCount;
    }

    public void setVoterCount(Long voterCount) {
        this.voterCount = voterCount;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public List<CountyResultRepresentation> getCountyResults() {
        return countyResults;
    }

    public void setCountyResults(List<CountyResultRepresentation> countyResults) {
        this.countyResults = countyResults;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountyRepresentation that = (CountyRepresentation) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(voterCount, that.voterCount) &&
                Objects.equals(districtId, that.districtId) &&
                Objects.equals(districtName, that.districtName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, voterCount, districtId, districtName);
    }

    @Override
    public String toString() {
        return "CountyRepresentation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", voterCount=" + voterCount +
                ", districtId=" + districtId +
                ", districtName='" + districtName + '\'' +
                '}';
    }
}
