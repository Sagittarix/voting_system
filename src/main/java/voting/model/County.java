package voting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import voting.model.results.CountyResult;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * Created by domas on 1/10/17.
 */
@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = { "name", "district_id" })
)
public class County {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;
  
    private Long voterCount;
    private String address;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.REMOVE},
            mappedBy = "county"
    )
    private List<CountyResult> countyResultList;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = {}
    )
    @JoinColumn(name = "district_id", nullable = false)
    @JsonIgnore
    private District district;

    @OneToOne(
            mappedBy = "county",
            cascade = {CascadeType.REMOVE},
            fetch = FetchType.EAGER
    )
    @JsonIgnore
    private CountyRep countyRep;

    public County() { }

    public County(String name, Long voterCount, String address) {
        this.name = name;
        this.voterCount = voterCount;
        this.address = address;
    }

    public boolean removeResult(CountyResult cr) {
        return this.getCountyResultList().remove(cr);
    }

    public Long getId() {
        return id;
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

    public CountyRep getCountyRep() {
        return countyRep;
    }

    public void setCountyRep(CountyRep countyRep) {
        countyRep.setCounty(this);
        this.countyRep = countyRep;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        County county = (County) o;
        return Objects.equals(id, county.id) &&
                Objects.equals(name, county.name) &&
                Objects.equals(voterCount, county.voterCount) &&
                Objects.equals(countyResultList, county.countyResultList) &&
                Objects.equals(district, county.district) &&
                Objects.equals(address, county.address) &&
                Objects.equals(countyRep, county.countyRep);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, voterCount, countyResultList, district, countyRep, address);
    }

    @Override
    public String toString() {
        return String.format("%s (id %d), adresas - %s, balsuotojų kiekis - %d", name, id, voterCount, address);
    }
}
