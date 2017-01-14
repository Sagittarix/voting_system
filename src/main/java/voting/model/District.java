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

//    public void setCounties(List<County> counties) {
//        this.counties = counties;
//    }

    public List<Candidate> getCandidates() {
        return candidates;
    }

//    public void setCandidates(List<Candidate> candidates) {
//        this.candidates = candidates;
//    }

    public void addCounty(County county) {
        counties.add(county);
        county.setDistrict(this);
    }

    public void addCandidate(Candidate candidate) {
        candidates.add(candidate);
        candidate.setDistrict(this);
    }

    @Override
    public String toString() {
        return String.format("%s: %d counties, %d candidates", name, counties.size(), candidates.size());
    }
}
