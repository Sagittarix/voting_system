package voting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    @JsonIgnore
    @ManyToOne
    private District district;
    private Long voterCount;

    public County() {
    }

    public County(String name, Long voterCount) {
        this.name = name;
        this.voterCount = voterCount;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
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
