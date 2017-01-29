package voting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
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
    private Long voterCount;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {},
            mappedBy = "county"
    )
    private List<CountyResult> countyResultList;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = {}
    )
    @JoinColumn(name = "district_id", nullable = false)
    private District district;

    public County() {
    }

    public County(String name, Long voterCount) {
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

    public List<CountyResult> getCountyResultList() {
        return countyResultList;
    }

    public void setCountyResultList(List<CountyResult> countyResultList) {
        this.countyResultList = countyResultList;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
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
