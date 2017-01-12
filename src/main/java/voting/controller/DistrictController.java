package voting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import voting.model.CountyRep;
import voting.model.District;
import voting.model.DistrictData;
import voting.service.DistrictService;

import java.util.List;

/**
 * Created by domas on 1/10/17.
 */

@RestController
@RequestMapping(path="/api/district")
public class DistrictController {

    private DistrictService districtService;

    @Autowired
    public DistrictController(DistrictService districtService) {
        this.districtService = districtService;
    }

    @GetMapping
    public List<District> getDistricts() {
        return districtService.getDistricts();
    }

    @GetMapping("/{id}")
    public District getDistrict(@PathVariable Long id) {
        return districtService.getDistrict(id);
    }

    @PostMapping
    public District addNewDistrict(@RequestBody DistrictData districtData) {
        return districtService.addNewDistrict(districtData);
    }

    @DeleteMapping("/{id}")
    public void deleteDistrict(@PathVariable Long id) {
        districtService.deleteDistrict(id);
    }


}
