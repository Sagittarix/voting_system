package voting.controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Errors;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import voting.dto.PartyData;
import voting.dto.PartyRepresentation;
import voting.exception.MultiErrorException;
import voting.model.Party;
import voting.service.PartyService;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by domas on 1/15/17.
 */


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path="/api/party")
public class PartyController {

    private final PartyService partyService;
    private final ObjectMapper mapper = new ObjectMapper();
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator javaxValidator = factory.getValidator();
    SpringValidatorAdapter validator = new SpringValidatorAdapter(javaxValidator);

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
        Party party = partyService.getParty(id);
        return new PartyRepresentation(party);
    }

    @PostMapping(consumes = "multipart/form-data")
    public PartyRepresentation saveParty(@RequestParam(name = "party") String partyDataString, @RequestParam(name = "file") MultipartFile multipartFile)
            throws IOException, CsvException {
        PartyData partyData = null;
        try {
            partyData = mapper.readValue(partyDataString, PartyData.class);
        } catch (JsonMappingException ex) {
            //TODO
            throw new IllegalArgumentException("Klaida registruojant partiją");
        }

        Errors bindingResult = new BeanPropertyBindingResult(partyData, "partyData");
        validator.validate(partyData, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new MultiErrorException("Klaida registruojant partiją " + partyData.getName(), bindingResult.getAllErrors());
        }

        return new PartyRepresentation(partyService.saveParty(partyData, multipartFile));
    }

    @DeleteMapping("/{id}")
    public void deleteParty(@PathVariable Long id) {
        partyService.deleteParty(id);
    }

    @PostMapping(value = "/{id}/candidates", consumes = "multipart/form-data")
    public PartyRepresentation setCandidateList(@PathVariable Long id, @RequestParam(name = "file") MultipartFile multipartFile)
            throws IOException, CsvException {
        return new PartyRepresentation(partyService.setCandidateList(id, multipartFile));
    }

    @DeleteMapping("/{id}/candidates")
    public void deleteCandidateList(@PathVariable Long id) {
        partyService.deleteCandidateList(id);
    }

}
