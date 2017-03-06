package voting.results;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import voting.dto.results.CountyMMResultDTO;
import voting.dto.results.CountyResultData;
import voting.dto.results.CountySMResultDTO;
import voting.exception.MultiErrorException;
import voting.results.model.result.CountyMMResult;
import voting.results.model.result.CountySMResult;

import javax.validation.Valid;

/**
 * Created by andrius on 1/24/17.
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/results")
public class CountyResultController {

    private ResultService resultService;

    @Autowired
    public CountyResultController(ResultService resultService) {
        this.resultService = resultService;
    }

    @GetMapping(path = "county/{id}/single-mandate")
    public CountySMResultDTO getCountySingleMandateResult(@PathVariable("id") Long countyId) {
        CountySMResult result = resultService.getCountySMResult(countyId);
        if (result == null) {
            return null;
        }
        return new CountySMResultDTO(result);
    }

    @GetMapping(path = "county/{id}/multi-mandate")
    public CountyMMResultDTO getCountyMultiMandateResult(@PathVariable("id") Long countyId) {
        CountyMMResult result = resultService.getCountyMMResult(countyId);
        if (result == null) {
            return null;
        }
        return new CountyMMResultDTO(result);
    }

    @PostMapping(path = "county/single-mandate")
    public CountySMResultDTO addCountySMResult(@Valid @RequestBody CountyResultData resultDTO, BindingResult result) {
        if (result.hasErrors()) {
            throw new MultiErrorException("Klaida apylinkės rezultatuose!", result.getAllErrors());
        }
        return new CountySMResultDTO(resultService.addCountySMResult(resultDTO));
    }

    @PostMapping(path = "county/multi-mandate")
    public CountyMMResultDTO addCountyMMResult(@Valid @RequestBody CountyResultData resultDTO, BindingResult result) {
        if (result.hasErrors()) {
            throw new MultiErrorException("Klaida apylinkės rezultatuose!", result.getAllErrors());
        }
        return new CountyMMResultDTO(resultService.addCountyMMResult(resultDTO));
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