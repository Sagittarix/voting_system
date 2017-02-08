package voting.results;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by andrius on 1/24/17.
 */

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
    public List<CountyResult> getAllMUltiMandateForCounty(@PathVariable Long county_id) {
        return countyResultService.getCountyResultsByMandate(county_id, false);
    }

    @PostMapping
    public CountyResult create(@RequestBody CountyResultDataModel crdm) {
        return countyResultService.save(crdm);
    }
}

// TODO perdaryti kai nelieka boolean fieldo