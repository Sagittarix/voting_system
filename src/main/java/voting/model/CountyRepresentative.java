package voting.model;

import javax.persistence.OneToOne;
import java.util.Objects;

/**
 * Created by domas on 1/21/17.
 */
public class CountyRepresentative extends Admin {

    @OneToOne
    private County county;

    public CountyRepresentative(String personalId, String firstName, String lastName, String role, String password_digest) {
        super(personalId, firstName, lastName, role, password_digest);
    }

    public County getCounty() {
        return county;
    }

    public void setCounty(County county) {
        this.county = county;
    }

}
