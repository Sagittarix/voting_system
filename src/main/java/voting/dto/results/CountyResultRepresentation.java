package voting.dto.results;

import voting.utils.RepresentationFactory;
import voting.model.results.CountyResult;

import java.util.Date;
import java.util.List;

/**
 * Created by andrius on 2/9/17.
 */

public class CountyResultRepresentation {

    private Long id;
    private int spoiledBallots;
    private boolean singleMandateSystem;
    private boolean confirmed;
    private Date createdOn;
    private Date confirmedOn;
    private List<UnitVotesRepresentation> unitVotesList;
    private Long countyId;

    public CountyResultRepresentation() { }

    public CountyResultRepresentation(CountyResult cr) {
        this.id = cr.getId();
        this.spoiledBallots = cr.getSpoiledBallots();
        this.singleMandateSystem = cr.isSingleMandateSystem();
        this.setConfirmed(cr.isConfirmed());
        this.createdOn = cr.getCreatedOn();
        this.confirmedOn = cr.getConfirmedOn();
        this.unitVotesList = RepresentationFactory.makeUnitsVotesListRepresentation(cr.getUnitVotesList());
        this.countyId = (cr.getCounty().getId());
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

    public List<UnitVotesRepresentation> getUnitVotesList() {
        return unitVotesList;
    }

    public void setUnitVotesList(List<UnitVotesRepresentation> unitVotesList) {
        this.unitVotesList = unitVotesList;
    }

    public Long getCountyId() {
        return countyId;
    }

    public void setCountyId(Long countyId) {
        this.countyId = countyId;
    }

    public Date getConfirmedOn() {
        return confirmedOn;
    }

    public void setConfirmedOn(Date confirmedOn) {
        this.confirmedOn = confirmedOn;
    }
}
