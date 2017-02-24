package voting.model.results;

import javax.persistence.*;
import java.util.List;

/**
 * Created by domas on 2/24/17.
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private int spoiledBallots;
    private boolean singleMandateSystem;

    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.LAZY,
            mappedBy = "countyResult"
    )
    private List<UnitVotes> unitVotesList;


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

    public boolean isSingleMandateSystem() {
        return singleMandateSystem;
    }

    public void setSingleMandateSystem(boolean singleMandateSystem) {
        this.singleMandateSystem = singleMandateSystem;
    }

    public List<UnitVotes> getUnitVotesList() {
        return unitVotesList;
    }

    public void setUnitVotesList(List<UnitVotes> unitVotesList) {
        this.unitVotesList = unitVotesList;
    }
}
