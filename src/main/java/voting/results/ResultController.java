package voting.results;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import voting.dto.results.*;
import voting.exception.MultiErrorException;
import voting.model.District;
import voting.results.model.result.CountyMMResult;
import voting.results.model.result.CountySMResult;
import voting.results.model.result.DistrictMMResult;
import voting.results.model.result.DistrictSMResult;
import voting.service.DistrictService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by andrius on 1/24/17.
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/results")
public class ResultController {

    private ResultService resultService;
    private DistrictService districtService;

    @Autowired
    public ResultController(ResultService resultService, DistrictService districtService) {
        this.resultService = resultService;
        this.districtService = districtService;
    }


    @GetMapping(path = "county/{id}/single-mandate")
    public CountySMResultDTO getCountySingleMandateResult(@PathVariable("id") Long countyId) {
        CountySMResult result = resultService.getCountySmResult(countyId);
        if (result == null) {
            return null;
        }
        return new CountySMResultDTO(result);
    }

    @GetMapping(path = "county/{id}/multi-mandate")
    public CountyMMResultDTO getCountyMultiMandateResult(@PathVariable("id") Long countyId) {
        CountyMMResult result = resultService.getCountyMmResult(countyId);
        if (result == null) {
            return null;
        }
        return new CountyMMResultDTO(result);
    }

    @GetMapping(path = "district/{id}/single-mandate")
    public DistrictSMResultDTO getDistrictSingleMandateResult(@PathVariable("id") Long districtId) {
        DistrictSMResult result = resultService.getDistrictSmResult(districtId);
        return new DistrictSMResultDTO(result);
    }

    @GetMapping(path = "district/{id}/multi-mandate")
    public DistrictMMResultDTO getDistrictMultiMandateResult(@PathVariable("id") Long districtId) {
        DistrictMMResult result = resultService.getDistrictMmResult(districtId);
        return new DistrictMMResultDTO(result);
    }

    @GetMapping(path = "single-mandate")
    public List<DistrictSmResultSummaryDTO> getSingleMandateResultsSummary() {
        return resultService.getAllDistrictSmResults()
                    .stream()
                    .map(DistrictSmResultSummaryDTO::new)
                    .collect(Collectors.toList());
    }

    @GetMapping(path = "multi-mandate")
    public MultiMandateResultSummaryDTO getMultiMandateResultsSummary() {
        return new MultiMandateResultSummaryDTO(resultService.getMmResultSummary());
    }

    @PostMapping(path = "county/single-mandate")
    public CountySMResultDTO addCountySMResult(@Valid @RequestBody CountyResultData resultData, BindingResult result) {
        if (result.hasErrors()) {
            throw new MultiErrorException("Klaida apylinkės rezultatuose!", result.getAllErrors());
        }
        return new CountySMResultDTO(resultService.addCountySmResult(resultData));
    }

    @PostMapping(path = "county/multi-mandate")
    public CountyMMResultDTO addCountyMMResult(@Valid @RequestBody CountyResultData resultData, BindingResult result) {
        if (result.hasErrors()) {
            throw new MultiErrorException("Klaida apylinkės rezultatuose!", result.getAllErrors());
        }
        return new CountyMMResultDTO(resultService.addCountyMmResult(resultData));
    }

    @PostMapping(path = "confirm")
    public void confirmCountyResult(@RequestParam Long resultId) {
        resultService.confirmCountyResult(resultId);
    }

    @DeleteMapping(path = "{id}")
    public void deleteCountyResult(@PathVariable(name = "id") Long resultId) {
        resultService.deleteCountyResult(resultId);
    }

}