package voting.dto.results;


import voting.dto.CountyShortRepresentation;
import voting.results.model.result.CountyResult;

import java.util.Date;

/**
 * Created by andrius on 2/9/17.
 */

public class CountyResultRepresentation extends ResultRepresentation {

    private CountyShortRepresentation county;
    private boolean confirmed;
    private Date createdOn;
    private Date confirmedOn;

    public CountyResultRepresentation() {
    }

    public CountyResultRepresentation(CountyResult result) {
        super(result);
        confirmed = result.isConfirmed();
        createdOn = result.getCreatedOn();
        confirmedOn = result.getConfirmedOn();
        county = result.getCounty() == null ? null : new CountyShortRepresentation(result.getCounty());
    }


    public CountyShortRepresentation getCounty() {
        return county;
    }

    public void setCounty(CountyShortRepresentation county) {
        this.county = county;
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
}
