package voting.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by domas on 1/10/17.
 */
@Entity
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    @Length(min = 3, max = 40, message = "Pavadinimas tarp 6 ir 40 simbolių")
    //@Pattern(regexp = "/^([a-zA-ZąčęėįšųūžĄČĘĖĮŠŲŪŽ0-9\\s][^qQwWxX]*)$/", message = "Pavadinimas neatitinka formato")
    private String name;

    // nebutinas - not_null - galima sukurti ir be apylinkiu
    @Valid
    @OneToMany(mappedBy = "district", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<County> counties = new ArrayList<>();

    @OneToMany(mappedBy = "district")
    private List<Candidate> candidates = new ArrayList<>();

    public District() {
    }

    public District(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<County> getCounties() {
        return counties;
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public void addCounty(County county) {
        counties.add(county);
        county.setDistrict(this);
    }

    public void removeCounty(County county) {
        counties.remove(county);
        county.setDistrict(null);
    }

    public void removeAllCounties() {
        counties.forEach(c -> c.setDistrict(null));
        counties = new ArrayList<County>();
    }

    public void addCandidate(Candidate candidate) {
        candidates.add(candidate);
        candidate.setDistrict(this);
    }

    public void removeCandidate(Candidate candidate) {
        candidates.remove(candidate);
        candidate.setDistrict(null);
    }

    public void removeAllCandidates() {
        candidates.forEach(c -> c.setDistrict(null));
        candidates = new ArrayList<Candidate>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        District district = (District) o;
        return Objects.equals(id, district.id) &&
                Objects.equals(name, district.name) &&
                Objects.equals(counties, district.counties) &&
                Objects.equals(candidates, district.candidates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, counties, candidates);
    }

    @Override
    public String toString() {
        return String.format("%s (id %d)", name, id);
    }
}
