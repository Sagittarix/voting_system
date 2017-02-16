package voting.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

/**
 * Created by domas on 1/12/17.
 */
public class PartyData {

    private Long id;

    @NotNull(message = "Partijos pavadinimas būtinas")
    @Length(min = 6, max = 40, message = "Partijos pavadinimas nuo 6 iki 40 simbolių")
    //@Pattern(regexp = "/^([a-zA-ZąčęėįšųūžĄČĘĖĮŠŲŪŽ0-9\\s][^qQwWxX]*)$/", message = "Pavadinimas neatitinka formato")
    private String name;

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
                Objects.equals(candidatesData, partyData.candidatesData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, candidatesData);
    }

    @Override
    public String toString() {
        return "PartyData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", candidatesData=" + candidatesData +
                '}';
    }
}
