package voting.results;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

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
        List<FieldError> errors = result.getFieldErrors();
        if (errors.size() > 0)
            throw new CountyResultFieldsErrorsException("CountyResult encountered validation errors", errors);
        return countyResultService.save(crdm);
    }
}

// TODO perdaryti kai nelieka boolean fieldo