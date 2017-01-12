package voting.model;

import javax.persistence.*;
import java.util.ArrayList;
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
    @OneToMany(mappedBy = "party", cascade = CascadeType.ALL)
    private List<Candidate> candidates = new ArrayList<>();

    public Party() {
    }

    public Party(String name, String shortName) {
        this.name = name;
        this.shortName = shortName;
    }

    public Long getId() {

        return id;
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public void addCandidate(Candidate candidate) {
        candidates.add(candidate);
        candidate.setParty(this);
    }
}
