package voting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import voting.dto.CandidateRepresentation;
import voting.dto.CountyData;
import voting.dto.CountyRepresentation;
import voting.exception.MultiErrorException;
import voting.model.County;
import voting.repository.CountyRepRepository;
import voting.repository.CountyRepository;
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

    private final CountyService countyService;
    private final CountyRepository countyRepository;

    @Autowired
    public CountyController(CountyService countyService, CountyRepository countyRepository) {
        this.countyService = countyService;
        this.countyRepository = countyRepository;
    }

    @PostMapping
    public CountyRepresentation create(@Valid @RequestBody CountyData countyData, BindingResult result) {
        if (result.hasErrors()) {
            throw new MultiErrorException("Klaida registruojant apylinkę " + countyData.getName(), result.getAllErrors());
        }
        return countyService.saveWithDistrict(countyData);
    }

    @GetMapping(path = "{id}")
    public CountyRepresentation getCounty(@PathVariable Long id) {
        return new CountyRepresentation(countyRepository.findOne(id));
    }

    @GetMapping(path = "{id}/candidates")
    public List<CandidateRepresentation> getSingleMandateCandidatesForCounty(@PathVariable Long id) {
        return countyService.getAllSingleMandateCandidatesForCounty(id);
    }

    @DeleteMapping
    public void delete(@RequestParam Long id) {
        countyService.delete(id);
    }

}
