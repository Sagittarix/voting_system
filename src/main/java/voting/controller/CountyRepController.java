package voting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import voting.dto.CountyRepresentativeRepresentation;
import voting.model.CountyRep;
import voting.service.CountyRepService;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by domas on 1/10/17.
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path="/api/county-rep")
public class CountyRepController {

    private CountyRepService countyRepService;

    @Autowired
    public CountyRepController(CountyRepService countyRepService) {
        this.countyRepService = countyRepService;
    }

    @GetMapping
    public List<CountyRep> getCountyReps() {
        return countyRepService.getCountyReps();
    }

    @GetMapping("/{id}")
    public CountyRepresentativeRepresentation getCountyRep(@PathVariable Long id) {
        return countyRepService.getCountyRep(id);
    }

    @PostMapping
    public CountyRep addNewCountyRep(@Valid @RequestBody CountyRep countyRep) {
        return countyRepService.addNewCountyRep(countyRep);
    }

    @DeleteMapping("/{id}")
    public void deleteCountyRep(@PathVariable Long id) {
        countyRepService.deleteCountyRep(id);
    }
}
