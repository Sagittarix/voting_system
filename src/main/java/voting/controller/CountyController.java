package voting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import voting.dto.CandidateRepresentation;
import voting.dto.CountyData;
import voting.exception.DTOMultiFieldsErrorsException;
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
        if (result.hasFieldErrors()) {
            List<FieldError> errs = result.getFieldErrors();
            throw new DTOMultiFieldsErrorsException("All County errors raised up to React", errs);
        }
        return countyService.saveWithDistrict(countyData);
    }

    @GetMapping(path = "{id}/candidates")
    public List<CandidateRepresentation> getSingleMandataCandidatesForCounty(@PathVariable Long id) {
        return countyService.getAllSingleMandateCandidatesForCounty(id);
    }

}
