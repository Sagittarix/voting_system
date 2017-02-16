package voting.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import voting.model.District;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by domas on 1/12/17.
 */

public class DistrictRepresentation {

    private Long id;
    private String name;
    private List<CountyRepresentation> counties;
    private List<CandidateRepresentation> candidates;

    public DistrictRepresentation() { }

    public DistrictRepresentation(District district) {
        this.id = district.getId();
        this.name = district.getName();
        this.counties = new ArrayList<CountyRepresentation>();
        if (district.getCounties() != null) {
            district.getCounties().forEach(c -> counties.add(new CountyRepresentation(c)));
        }
        this.candidates = new ArrayList<CandidateRepresentation>();
        if (district.getCandidates() != null) {
            district.getCandidates().forEach(c -> candidates.add(new CandidateRepresentation(c)));
        }
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

    public List<CountyRepresentation> getCounties() {
        return counties;
    }

    public void setCounties(List<CountyRepresentation> counties) {
        this.counties = counties;
    }

    public List<CandidateRepresentation> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<CandidateRepresentation> candidates) {
        this.candidates = candidates;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DistrictRepresentation that = (DistrictRepresentation) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(counties, that.counties) &&
                Objects.equals(candidates, that.candidates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, counties, candidates);
    }

    @Override
    public String toString() {
        return "DistrictRepresentation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", counties=" + counties +
                ", candidates=" + candidates +
                '}';
    }
}
