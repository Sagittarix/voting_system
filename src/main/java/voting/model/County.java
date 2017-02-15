package voting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;
import voting.results.CountyResult;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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

    @Column(nullable = false)       // + buvo unique = true - REIKALINGAS? "Senamiescio" apylinke gali buti > 1 apygardoje?
    @NotNull(message = "Pavadinimas būtinas")
    @Length(min=6, max=40, message = "Pavadinimas tarp 6 ir 40 simbolių")
    //@Pattern(regexp = "/^([a-zA-ZąčęėįšųūžĄČĘĖĮŠŲŪŽ0-9\\s][^qQwWxX]*)$/", message = "Pavadinimas neatitinka formato")
    private String name;
  
    @Min(value = 100, message = "Mažiausiai gyventojų - 100")
    @Max(value = 3000000, message = "Daugiausiai gyventojų - 3_000_000")
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

    //@JoinColumn(name = "district_id", nullable = false) // atstatyti before production
    @JoinColumn(name = "district_id", nullable = true) // comment-out before production
    @NotNull(message = "Negalima išsaugoti be apygardos")
    @JsonIgnore
    private District district;

    public County() { }

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
        return String.format("%s (id %d), balsuotojų kiekis - ", name, id, voterCount);
    }
}
