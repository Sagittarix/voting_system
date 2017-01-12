package voting.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by domas on 1/10/17.
 */
@Entity
public class Party {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String shortName;
    @OneToMany(mappedBy = "party")
    private List<Candidate> candidates;

    public Party() {
    }

    public Party(String name, String shortName, List<Candidate> candidates) {
        this.name = name;
        this.shortName = shortName;
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

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }
}
