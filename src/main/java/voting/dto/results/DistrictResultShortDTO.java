package voting.dto.results;

import voting.results.model.result.DistrictResult;
import voting.results.model.result.DistrictSMResult;
import voting.utils.Constants;

/**
 * Created by domas on 2/27/17.
 */
public class DistrictResultShortDTO {


    private Long id;
    private String link;


    public DistrictResultShortDTO(DistrictResult result) {
        this.id = result.getId();
        this.link = String.format("%s/results/district/%d", Constants.API_ROOT_URL, id);;
        if (result instanceof DistrictSMResult) {
            this.link.concat("/single-mandate");
        } else {
            this.link.concat("/multi-mandate");
        }
    }

    public Long getId() {
        return id;
    }

    public String getLink() {
        return link;
    }
}
