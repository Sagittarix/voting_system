package voting.dto.results.unused;

import voting.results.model.result.CountyResult;
import voting.results.model.result.CountySMResult;
import voting.utils.Constants;

import java.util.Date;

/**
 * Created by domas on 3/2/17.
 */
public class CountyResultShortDTO {

    private Long id;
    private Date createdOn;
    private Date confirmedOn;
    private String link;

    public CountyResultShortDTO(CountyResult result) {
        this.id = result.getId();
        this.createdOn = result.getCreatedOn();
        this.confirmedOn = result.getConfirmedOn();
        this.link = constructLink(result);
    }

    private String constructLink(CountyResult result) {
        String link = String.format("%s/results/county/%d", Constants.API_ROOT_URL, id);;
        if (result instanceof CountySMResult) {
            link = link.concat("/single-mandate");
        } else {
            link = link.concat("/multi-mandate");
        }
        return link;
    }


    public Long getId() {
        return id;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public Date getConfirmedOn() {
        return confirmedOn;
    }

    public String getLink() {
        return link;
    }
}
