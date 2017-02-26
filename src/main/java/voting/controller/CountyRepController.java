package voting.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import voting.dto.CountyRepresentativeData;
import voting.dto.CountyRepresentativeRepresentation;
import voting.exception.MultiErrorException;
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
    private static final Logger logger = Logger.getLogger(CountyRepController.class);

    @Autowired
    public CountyRepController(CountyRepService countyRepService) {
        this.countyRepService = countyRepService;
    }

    @GetMapping
    public List<CountyRepresentativeRepresentation> getCountyReps() {
        List<CountyRepresentativeRepresentation> collection = countyRepService.getCountyReps()
                                                                              .stream()
                                                                              .map(CountyRepresentativeRepresentation::new)
                                                                              .collect(Collectors.toList());
        logger.debug("Collection extracted - " + collection);
        return collection;
    }

    @GetMapping("/{id}")
    public CountyRepresentativeRepresentation getCountyRep(@PathVariable Long id) {
        CountyRepresentativeRepresentation crr = countyRepService.getCountyRep(id);
        logger.debug("Entity extracted - " + crr.toString());
        return crr;
    }

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
