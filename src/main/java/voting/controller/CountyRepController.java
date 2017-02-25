package voting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import voting.dto.CountyRepresentativeData;
import voting.dto.CountyRepresentativeRepresentation;
import voting.exception.MultiErrorException;
import voting.model.CountyRep;
import voting.service.CountyRepService;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<CountyRepresentativeRepresentation> getCountyReps() {
        return countyRepService.getCountyReps()
                               .stream()
                               .map(CountyRepresentativeRepresentation::new)
                               .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CountyRepresentativeRepresentation getCountyRep(@PathVariable Long id) {
        return countyRepService.getCountyRep(id);
    }

    /*@PostMapping
    public CountyRep addNewCountyRep(@Valid @RequestBody CountyRep countyRep, BindingResult result) {
        if (result.hasErrors()) {
            throw new MultiErrorException("Klaida registruojant apygardos atstovą " + countyRep.getFirstName() + " "
                    + countyRep.getLastName(), result.getAllErrors());
        }
        return countyRepService.addNewCountyRep(countyRep);
    }*/


    @PostMapping
    public CountyRepresentativeRepresentation addNewCountyRep(@Valid @RequestBody CountyRepresentativeData countyRepData, BindingResult result) {
        if (result.hasErrors()) {
            throw new MultiErrorException("Klaida registruojant apygardos atstovą " + countyRepData.getFirstName() + " "
                    + countyRepData.getLastName(), result.getAllErrors());
        }
        return countyRepService.addNewCountyRep(countyRepData);
    }

    @DeleteMapping("/{id}")
    public void deleteCountyRep(@PathVariable Long id) {
        countyRepService.deleteCountyRep(id);
    }
}
