package voting.model;

import javax.persistence.ManyToOne;

/**
 * Created by domas on 1/12/17.
 */
public class CountyData {

    private Long id;
    private String name;
    private Long voterCount;

    public CountyData() {
    }

    public CountyData(Long id, String name, Long voterCount) {

        this.id = id;
        this.name = name;
        this.voterCount = voterCount;
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
}
