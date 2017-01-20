package voting.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

/**
 * Created by domas on 1/12/17.
 */
public class PartyData {

    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String shortName;
    @Valid
    @NotNull
    @JsonProperty("candidates")
    private List<CandidateData> candidatesData;


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

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public List<CandidateData> getCandidatesData() {
        return candidatesData;
    }

    public void setCandidatesData(List<CandidateData> candidatesData) {
        this.candidatesData = candidatesData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PartyData partyData = (PartyData) o;
        return Objects.equals(id, partyData.id) &&
                Objects.equals(name, partyData.name) &&
                Objects.equals(shortName, partyData.shortName) &&
                Objects.equals(candidatesData, partyData.candidatesData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, shortName, candidatesData);
    }

    @Override
    public String toString() {
        return "PartyData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", shortName='" + shortName + '\'' +
                ", candidatesData=" + candidatesData +
                '}';
    }
}
