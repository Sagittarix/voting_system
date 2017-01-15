package voting.model;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * Created by domas on 1/12/17.
 */
public class CountyData {

    private Long id;
    @NotNull
    private String name;
    private Long voterCount;


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
}
