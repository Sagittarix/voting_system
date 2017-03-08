package voting.dto.results;

import voting.dto.district.DistrictShortDTO;
import voting.model.District;
import voting.results.model.result.*;
import voting.utils.Constants;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
