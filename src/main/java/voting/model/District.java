package voting.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by domas on 1/10/17.
 */
@Entity
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "district", cascade = CascadeType.ALL)
    private List<County> counties;
    @OneToMany(mappedBy = "district", cascade = CascadeType.ALL)
    private List<Candidate> candidates;

    public District() {
    }

    public District(String name, List<County> counties, List<Candidate> candidates) {
        this.name = name;
        this.counties = counties;
        this.candidates = candidates;
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

    public List<County> getCounties() {
        return counties;
    }

    public void setCounties(List<County> counties) {
        this.counties = counties;
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }

    public void addCounty(County county) {
        if (counties == null) {
            counties = new ArrayList<>();
        }
        counties.add(county);
        county.setDistrict(this);
    }

    public void addCandidate(Candidate candidate) {
        if (candidates == null) {
            candidates = new ArrayList<>();
        }
        candidates.add(candidate);
        candidate.setDistrict(this);
    }

    @Override
    public String toString() {
        return String.format("%s: %d counties, %d candidates", name, counties.size(), candidates.size());
    }
}
