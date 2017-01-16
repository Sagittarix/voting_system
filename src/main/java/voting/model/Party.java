package voting.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    @OneToMany(mappedBy = "party", orphanRemoval = true)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Party party = (Party) o;
        return Objects.equals(id, party.id) &&
                Objects.equals(name, party.name) &&
                Objects.equals(shortName, party.shortName) &&
                Objects.equals(candidates, party.candidates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, shortName, candidates);
    }

    @Override
    public String toString() {
        return "Party{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", shortName='" + shortName + '\'' +
                ", candidates=" + candidates +
                '}';
    }
}
