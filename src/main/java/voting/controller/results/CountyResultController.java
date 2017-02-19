package voting.controller.results;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import voting.dto.CountyRepresentation;
import voting.dto.results.CountyResultDataModel;
import voting.dto.results.CountyResultRepresentation;
import voting.exception.MultiErrorException;
import voting.model.results.CountyResult;
import voting.repository.results.CountyResultRepository;
import voting.service.results.CountyResultService;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by andrius on 1/24/17.
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/county-results")
public class CountyResultController {

    private CountyResultRepository countyResultRepository;
    private CountyResultService countyResultService;

    @Autowired
    public CountyResultController(CountyResultRepository countyResultRepository, CountyResultService countyResultService) {
        this.countyResultRepository = countyResultRepository;
        this.countyResultService = countyResultService;
    }

    @GetMapping
    public List<CountyResult> getAll() {
        return countyResultRepository.findAll();
    }

    @GetMapping(path = "single-mandate")
    public List<CountyResult> getAllForSingleMandate() {
        return countyResultService.getAllForSingleMandate();
    }

    @GetMapping(path = "multi-mandate")
    public List<CountyResult> getAllForMultiMandate() {
        return countyResultService.getAllForMultiMandate();
    }

    @GetMapping(path = "{id}/single-mandate")
    public List<CountyResult> getAllSingleMandateForCounty(@PathVariable Long county_id) {
        return countyResultService.getCountyResultsByMandate(county_id, true);
    }

    @GetMapping(path = "{id}/multi-mandate")
    public List<CountyResult> getAllMultiMandateForCounty(@PathVariable Long county_id) {
        return countyResultService.getCountyResultsByMandate(county_id, false);
    }

    @PostMapping
    public CountyResultRepresentation create(@Valid @RequestBody CountyResultDataModel crdm, BindingResult result) {
        if (result.hasErrors())
            throw new MultiErrorException("CountyResult encountered validation errors", result.getAllErrors());
        return countyResultService.save(crdm);
    }

    @PostMapping(path = "confirm")
    public CountyRepresentation confirmResultForCounty(@RequestParam Long countyId, @RequestParam boolean isSingleMandate) {
        return countyResultService.confirmResultForCounty(countyId, isSingleMandate);
    }

    @DeleteMapping(path = "county")
    public CountyRepresentation deleteResultForCounty(@RequestParam Long countyId, @RequestParam boolean isSingleMandate) {
        return countyResultService.deleteResultForCounty(countyId, isSingleMandate);
    }
}