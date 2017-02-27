package voting.results.model.votecount;


import voting.results.model.result.Result;

import javax.persistence.*;

/**
 * Created by domas on 2/22/17.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class VoteCount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long votes;

    @ManyToOne
    @JoinColumn(name = "result_id")
    private Result result;

    public VoteCount() {
    }

    public VoteCount(Long votes) {
        this.votes = votes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVotes() {
        return votes;
    }

    public void setVotes(Long votes) {
        this.votes = votes;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
