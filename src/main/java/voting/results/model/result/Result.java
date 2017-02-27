package voting.results.model.result;


import voting.results.model.votecount.VoteCount;

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

    private int spoiledBallots;

    @OneToMany(mappedBy="result", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<VoteCount> voteCounts = new ArrayList<>();

    public void addVoteCount(VoteCount voteCount) {
        voteCounts.add(voteCount);
        voteCount.setResult(this);
    }

    public Result() {
        this.spoiledBallots = 0;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSpoiledBallots() {
        return spoiledBallots;
    }

    public void setSpoiledBallots(int spoiledBallots) {
        this.spoiledBallots = spoiledBallots;
    }

    public List<VoteCount> getVoteCounts() {
        return voteCounts;
    }

    public void setVoteCounts(List<VoteCount> voteCounts) {
        this.voteCounts = voteCounts;
    }


    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", spoiledBallots=" + spoiledBallots +
                '}';
    }



}
