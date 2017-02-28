package voting.dto;

import voting.model.District;

/**
 * Created by domas on 2/27/17.
 */
public class DistrictShortRepresentation {

    private Long id;
    private String name;

    public DistrictShortRepresentation() {
    }

    public DistrictShortRepresentation(District district) {
        id = district.getId();
        name = district.getName();
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
}
