package voting.results.model.result;


import voting.results.model.votecount.Vote;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by domas on 2/22/17.
 */
@Entity
@DiscriminatorColumn(name = "type")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long spoiledBallots = 0L;
    private Long totalBallots = 0L;

    @OneToMany(mappedBy="result", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Vote> unitVotes = new ArrayList<>();

    public Result() {
        this.spoiledBallots = 0L;
        this.totalBallots = 0L;
    }


    public void combineResults(Result r) {
        this.spoiledBallots += r.getSpoiledBallots();
        this.totalBallots += r.getTotalBallots();
        combineVotes(r.getUnitVotes());
    }

    private void combineVotes(List<Vote> voteList) {
        Map<Long, Long> votemap = convertToMap(voteList);
        unitVotes.stream().forEach(v -> v.setVoteCount(v.getVoteCount() + votemap.get(v.getUnitId())));
    }

    private Map<Long,Long> convertToMap(List<Vote> unitVotes) {
        return unitVotes.stream().collect(Collectors.toMap(Vote::getUnitId, Vote::getVoteCount));
    }

    public void addVote(Vote vote) {
        this.unitVotes.add(vote);
        vote.setResult(this);
        this.totalBallots += vote.getVoteCount();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSpoiledBallots() {
        return spoiledBallots;
    }

    public void setSpoiledBallots(Long spoiledBallots) {
        this.spoiledBallots = spoiledBallots;
    }

    public Long getTotalBallots() {
        return totalBallots;
    }

    public void setTotalBallots(Long totalBallots) {
        this.totalBallots = totalBallots;
    }

    public List<Vote> getUnitVotes() {
        return unitVotes;
    }

    public void setUnitVotes(List<Vote> unitVotes) {
        this.unitVotes = unitVotes;
    }


    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", spoiledBallots=" + spoiledBallots +
                '}';
    }



}
