package voting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        County county = (County) o;
        return Objects.equals(id, county.id) &&
                Objects.equals(name, county.name) &&
                Objects.equals(district, county.district) &&
                Objects.equals(voterCount, county.voterCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, district, voterCount);
    }

    @Override
    public String toString() {
        return "County{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", district=" + district +
                ", voterCount=" + voterCount +
                '}';
    }
}
