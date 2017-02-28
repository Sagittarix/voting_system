package voting.dto;

import voting.model.Party;

/**
 * Created by domas on 2/27/17.
 */
public class PartyShortRepresentation {

    private Long id;
    private String name;

    public PartyShortRepresentation() {
    }

    public PartyShortRepresentation(Party party) {
        id = party.getId();
        name = party.getName();
    }

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
}
