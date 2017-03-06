package voting.results.model.result;


import voting.results.model.votecount.Vote;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    private Long spoiledBallots;
    private Long totalBallots;

    @OneToMany(mappedBy="result", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Vote> unitVotes = new ArrayList<>();

    public Result() {
        this.spoiledBallots = 0L;
    }

    public void addVote(Vote vote) {
        this.unitVotes.add(vote);
        vote.setResult(this);
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
