package voting.dto;

import voting.model.County;

/**
 * Created by domas on 2/27/17.
 */
public class CountyShortRepresentation {

    private Long id;
    private String name;

    public CountyShortRepresentation() {
    }

    public CountyShortRepresentation(County county) {
        id = county.getId();
        name = county.getName();
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
