package voting.controller.results;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import voting.dto.results.CountyResultDataModel;
import voting.dto.results.CountyResultRepresentation;
import voting.exception.MultiErrorException;
import voting.model.results.CountyResult;
import voting.service.results.CountyResultService;
import voting.utils.RepresentationFactory;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by andrius on 1/24/17.
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/county-results")
public class CountyResultController {

    private CountyResultService countyResultService;

    @Autowired
    public CountyResultController(CountyResultService countyResultService) {
        this.countyResultService = countyResultService;
    }

    @GetMapping
    public List<CountyResult> getAll() {
        return countyResultService.getAllCountyResults();
    }

    @GetMapping(path = "single-mandate")
    public List<CountyResult> getSingleMandateResults() {
        return countyResultService.getAllSingleMandateResults();
    }

    @GetMapping(path = "multi-mandate")
    public List<CountyResult> getMultiMandateResults() {
        return countyResultService.getAllMultiMandateResults();
    }

    @GetMapping(path = "{id}/single-mandate")
    public CountyResultRepresentation getSingleMandateResult(@PathVariable("id") Long countyId) {
        CountyResult cr = countyResultService.getSingleMandateResult(countyId);
        if (cr != null) {
            return RepresentationFactory.makeRepresentationOf(cr);
        } else {
            return null;
        }
    }

    @GetMapping(path = "{id}/multi-mandate")
    public CountyResultRepresentation getAllMultiMandateForCounty(@PathVariable("id") Long countyId) {
        CountyResult cr = countyResultService.getMultiMandateResult(countyId);
        if (cr != null) {
            return RepresentationFactory.makeRepresentationOf(cr);
        } else {
            return null;
        }
    }

    @PostMapping
    public CountyResultRepresentation create(@Valid @RequestBody CountyResultDataModel crdm, BindingResult result) {
        if (result.hasErrors()) {
            throw new MultiErrorException("Klaida apylinkės rezultatuose!", result.getAllErrors());
        }
        return RepresentationFactory.makeRepresentationOf(countyResultService.save(crdm));
    }

//    @PostMapping(path = "confirm")
//    public CountyRepresentation confirmResultForCounty(@RequestParam Long countyId, @RequestParam boolean isSingleMandate) {
//        return countyResultService.confirmResultForCounty(countyId, isSingleMandate);
//    }
//
//    @DeleteMapping(path = "county")
//    public CountyRepresentation deleteResultForCounty(@RequestParam Long countyId, @RequestParam boolean isSingleMandate) {
//        return countyResultService.deleteResultForCounty(countyId, isSingleMandate);
//    }
}