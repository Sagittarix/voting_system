package voting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import voting.model.CountyRep;
import voting.service.CountyRepService;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by domas on 1/10/17.
 */
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
    public CountyRep getAllCountyRep(@PathVariable Long id) {
        return countyRepService.getCountyRep(id);
    }

    @PostMapping
    public CountyRep addNewCountyRep(@RequestBody CountyRep countyRep) {
        return countyRepService.addNewCountyRep(countyRep);
    }

    @DeleteMapping("/{id}")
    public void deleteCountyRep(@PathVariable Long id) {
        countyRepService.deleteCountyRep(id);
    }
}
