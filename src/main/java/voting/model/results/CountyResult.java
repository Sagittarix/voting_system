package voting.model.results;

import voting.model.County;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Created by andrius on 1/24/17.
 */

@Entity
public class CountyResult extends Result {

    private boolean confirmed;
    private Date createdOn;
    private Date confirmedOn;


    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = {}
    )
    @JoinColumn(name = "county_id", nullable = false)
    private County county;

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

    public County getCounty() {
        return county;
    }

    public void setCounty(County county) {
        this.county = county;
    }


}
