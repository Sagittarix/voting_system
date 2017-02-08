package voting.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Created by domas on 1/12/17.
 */
public class CountyData {

    private Long id;

    @NotNull(message = "Spring - Pavadinimas būtinas")
    @Length(min=6, max=40, message = "Spring - Pavadinimas tarp 6 ir 40 simbolių")
    private String name;

    @Min(value = 500, message = "Spring - Mažiausiai gyventojų - 500")
    @Max(value = 3000000, message = "Spring - Daugiausiai gyventojų - 3_000_000")
    private Long voterCount;

    // nebutinas.
    private Long districtId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountyData that = (CountyData) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(voterCount, that.voterCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, voterCount);
    }

    @Override
    public String toString() {
        return "CountyData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", voterCount=" + voterCount +
                '}';
    }
}
