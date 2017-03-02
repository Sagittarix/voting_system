package voting.dto.countyrep;

import voting.dto.county.CountyShortDTO;
import voting.dto.district.DistrictShortDTO;
import voting.model.CountyRep;

/**
 * Created by domas on 1/12/17.
 */
public class CountyRepresentativeDTO {

    private Long id;
    private String personalId;
    private String firstName;
    private String lastName;
    private String email;
    private CountyShortDTO county;
    private DistrictShortDTO district;


    public CountyRepresentativeDTO(CountyRep cr) {
        this.id = cr.getId();
        this.personalId =
        this.firstName = cr.getFirstName();
        this.lastName = cr.getLastName();
        this.email = cr.getEmail();
        this.county = new CountyShortDTO(cr.getCounty());
        this.district = new DistrictShortDTO(cr.getCounty().getDistrict());
    }

    public Long getId() {
        return id;
    }

    public String getPersonalId() {
        return personalId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public CountyShortDTO getCounty() {
        return county;
    }

    public DistrictShortDTO getDistrict() {
        return district;
    }
}
