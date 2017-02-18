package voting.model.results;

import voting.model.County;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by andrius on 1/24/17.
 */

@Entity
public class CountyResult {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int spoiledBallots;
    private boolean singleMandateSystem;
    private boolean confirmed;
    private Date createdOn;
    private Date confirmedOn;

    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.LAZY,
            mappedBy = "countyResult"
    )
    private List<UnitVotes> unitVotesList;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = {}
    )
    @JoinColumn(name = "county_id", nullable = false)
    private County county;

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

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getConfirmedOn() {
        return confirmedOn;
    }

    public void setConfirmedOn(Date confirmedOn) {
        this.confirmedOn = confirmedOn;
    }

    public List<UnitVotes> getUnitVotesList() {
        return unitVotesList;
    }

    public void setUnitVotesList(List<UnitVotes> unitVotesList) {
        this.unitVotesList = unitVotesList;
    }

    public County getCounty() {
        return county;
    }

    public void setCounty(County county) {
        this.county = county;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CountyResult that = (CountyResult) o;

        if (spoiledBallots != that.spoiledBallots) return false;
        if (singleMandateSystem != that.singleMandateSystem) return false;
        if (confirmed != that.confirmed) return false;
        if (!id.equals(that.id)) return false;
        if (!createdOn.equals(that.createdOn)) return false;
        if (!confirmedOn.equals(that.confirmedOn)) return false;
        if (!unitVotesList.equals(that.unitVotesList)) return false;
        return county.equals(that.county);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + spoiledBallots;
        result = 31 * result + (singleMandateSystem ? 1 : 0);
        result = 31 * result + (confirmed ? 1 : 0);
        result = 31 * result + createdOn.hashCode();
        result = 31 * result + confirmedOn.hashCode();
        result = 31 * result + unitVotesList.hashCode();
        result = 31 * result + county.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "CountyResult{" +
                "id=" + id +
                ", spoiledBallots=" + spoiledBallots +
                ", singleMandateSystem=" + singleMandateSystem +
                ", confirmed=" + confirmed +
                ", createdOn=" + createdOn +
                ", confirmedOn=" + confirmedOn +
                ", unitVotesList=" + unitVotesList +
                ", county=" + county +
                '}';
    }
}
