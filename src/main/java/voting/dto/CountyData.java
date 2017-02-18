package voting.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Objects;

/**
 * Created by domas on 1/12/17.
 */

public class CountyData {

    @NotNull(message = "Pavadinimas būtinas")
    @Length(min=6, max=40, message = "Apylinkės pavadinimas tarp 6 ir 40 simbolių")
    private String name;

    @Min(value = 500, message = "Mažiausiai balsuotojų - 500")
    @Max(value = 3000000, message = "Daugiausiai balsuotojų - 3_000_000")
    private Long voterCount;

    @NotNull(message = "Adresas būtinas")
    @Length(min = 6, message = "Adresas per trumpas (min. 6 simboliai)")
    private String address;

    private Long districtId;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getVoterCount() {
        return voterCount;
    }

    public void setVoterCount(Long voterCount) {
        this.voterCount = voterCount;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountyData that = (CountyData) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(voterCount, that.voterCount) &&
                Objects.equals(districtId, that.districtId) &&
                Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, voterCount, districtId, address);
    }

    @Override
    public String toString() {
        return "CountyData{" +
                "name='" + name + '\'' +
                ", voterCount=" + voterCount +
                ", districtId=" + districtId +
                ", address=" + address +
                '}';
    }
}
