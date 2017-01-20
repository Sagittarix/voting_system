package voting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import voting.model.Party;
import voting.dto.PartyData;
import voting.service.PartyService;

import javax.validation.Valid;
import java.util.List;

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
    public List<Party> getParties() {
        return partyService.getParties();
    }

    @GetMapping("/{id}")
    public Party getParty(@PathVariable Long id) {
        return partyService.getParty(id);
    }

    @PostMapping
    public Party addNewParty(@Valid @RequestBody PartyData partyData) {
        return partyService.addNewParty(partyData);
    }

    @DeleteMapping("/{id}")
    public void deleteParty(@PathVariable Long id) {
        partyService.deleteParty(id);
    }
}
