package voting.model;

import javax.persistence.*;

/**
 * Created by domas on 1/10/17.
 */
@Entity
public class County {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToOne
    private District district;
    private Long voterCount;

    public County() {
    }

    public County(String name, District district, Long voterCount) {
        this.name = name;
        this.district = district;
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

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Long getVoterCount() {
        return voterCount;
    }

    public void setVoterCount(Long voterCount) {
        this.voterCount = voterCount;
    }

    @Override
    public String toString() {
        return String.format("%s, %s", name, district.getName());
    }
}
