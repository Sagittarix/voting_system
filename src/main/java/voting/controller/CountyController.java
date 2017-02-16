package voting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import voting.dto.CandidateRepresentation;
import voting.dto.CountyData;
import voting.exception.MultiErrorException;
import voting.model.County;
import voting.service.CountyService;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by andrius on 1/21/17.
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/county")
public class CountyController {

    @Autowired
    private CountyService countyService;

    @PostMapping
    public County create(@Valid @RequestBody CountyData countyData, BindingResult result) {
        if (result.hasErrors()) {
            throw new MultiErrorException("Klaida registruojant apygardą " + countyData.getName(), result.getAllErrors());
        }
        return countyService.saveWithDistrict(countyData);
    }

    @GetMapping(path = "{id}/candidates")
    public List<CandidateRepresentation> getSingleMandateCandidatesForCounty(@PathVariable Long id) {
        return countyService.getAllSingleMandateCandidatesForCounty(id);
    }

}
