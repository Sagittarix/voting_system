package voting.dto;

import voting.model.CountyRep;

/**
 * Created by domas on 2/27/17.
 */
public class CountyRepShortRepresentation {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private CountyShortRepresentation county;

    public CountyRepShortRepresentation() {
    }

    public CountyRepShortRepresentation(CountyRep countyRep) {
        id = countyRep.getId();
        firstName = countyRep.getFirstName();
        lastName = countyRep.getLastName();
        email = countyRep.getEmail();
        county = countyRep.getCounty() == null ? null : new CountyShortRepresentation(countyRep.getCounty());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CountyShortRepresentation getCounty() {
        return county;
    }

    public void setCounty(CountyShortRepresentation county) {
        this.county = county;
    }
}
