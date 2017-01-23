package voting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import voting.dto.PartyData;
import voting.dto.PartyRepresentation;
import voting.service.PartyService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by domas on 1/15/17.
 */
@RestController
@RequestMapping(path="/api/party")
public class PartyController {

    private final PartyService partyService;

    @Autowired
    public PartyController(PartyService partyService) {
        this.partyService = partyService;
    }

    @GetMapping
    public List<PartyRepresentation> getParties() {
        return partyService.getParties().stream().map(p -> new PartyRepresentation(p)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PartyRepresentation getParty(@PathVariable Long id) {
        return new PartyRepresentation(partyService.getParty(id));
    }

    @PostMapping
    public PartyRepresentation addNewParty(@Valid @RequestBody PartyData partyData) {
        return new PartyRepresentation(partyService.addNewParty(partyData));
    }

    @DeleteMapping("/{id}")
    public void deleteParty(@PathVariable Long id) {
        partyService.deleteParty(id);
    }
}
