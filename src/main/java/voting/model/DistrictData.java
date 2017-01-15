package voting.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

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

}
