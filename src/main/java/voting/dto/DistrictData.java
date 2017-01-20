package voting.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

/**
 * Created by domas on 1/12/17.
 */

public class DistrictData {

    private Long id;
    @NotNull
    private String name;
    @Valid
    @NotNull
    @JsonProperty("counties")
    private List<CountyData> countiesData;


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

    public List<CountyData> getCountiesData() {
        return countiesData;
    }

    public void setCountiesData(List<CountyData> countiesData) {
        this.countiesData = countiesData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DistrictData that = (DistrictData) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(countiesData, that.countiesData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, countiesData);
    }

    @Override
    public String toString() {
        return "DistrictData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", countiesData=" + countiesData +
                '}';
    }
}
