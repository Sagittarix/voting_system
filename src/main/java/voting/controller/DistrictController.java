package voting.controller;

import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import voting.dto.*;
import voting.exception.MultiErrorException;
import voting.model.District;
import voting.service.DistrictService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by domas on 1/10/17.
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/api/district")
public class DistrictController {

    private DistrictService districtService;

    @Autowired
    public DistrictController(DistrictService districtService) {
        this.districtService = districtService;
    }


    @GetMapping
    public List<DistrictRepresentation> getDistricts() {
        return districtService.getDistricts().stream().map(DistrictRepresentation::new).collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public DistrictRepresentation getDistrict(@PathVariable Long id) {
        District district = districtService.getDistrict(id);
        return new DistrictRepresentation(district);
    }

    @PostMapping
    public DistrictRepresentation addNewDistrict(@Valid @RequestBody DistrictData districtData, BindingResult result) {
        if (result.hasErrors()) {
            throw new MultiErrorException("Klaida registruojant apygardą " + districtData.getName(), result.getAllErrors());
        }
        return new DistrictRepresentation(districtService.addNewDistrict(districtData));
    }

    @PatchMapping("{id}/update")
    public DistrictRepresentation updateDistrict(
            @PathVariable Long id,
            @Valid @RequestBody DistrictData districtData,
            BindingResult result) {

        if (result.hasErrors()) {
            throw new MultiErrorException("Klaida atnaujinant apygardą " + districtData.getName(), result.getAllErrors());
        }
        return new DistrictRepresentation(districtService.updateDistrict(districtData, id));
    }



    @DeleteMapping("{id}")
    public void deleteDistrict(@PathVariable Long id) {
        districtService.deleteDistrict(id);
    }

    @DeleteMapping("{id}/candidates")
    public void deleteCandidateList(@PathVariable Long id) {
        districtService.deleteCandidateList(id);
    }

    @PostMapping(value = "{id}/candidates", consumes = "multipart/form-data")
    public DistrictRepresentation setCandidateList(@PathVariable Long id, @RequestParam(name = "file") MultipartFile file)
            throws IOException, CsvException {
        return new DistrictRepresentation(districtService.setCandidateList(id, file));
    }

    @GetMapping("/{id}/candidates")
    public List<CandidateRepresentation> getCandidateList(@PathVariable Long id) {
        return districtService.getCandidateList(id).stream()
                .map(CandidateRepresentation::new)
                .collect(Collectors.toList());
    }

    @PostMapping("/{id}/add-county")
    public DistrictRepresentation addCounty(@PathVariable("id") Long districtId, @Valid @RequestBody CountyData countyData, BindingResult result) {
        if (result.hasErrors()) {
            throw new MultiErrorException("Klaida registruojant apylinkę " + countyData.getName(), result.getAllErrors());
        }
        return new DistrictRepresentation(districtService.addCounty(districtId, countyData));
    }

    @PostMapping("/{id}/update-county/{cid}")
    public CountyRepresentation updateCounty(@PathVariable("id") Long districtId, @PathVariable("cid") Long cid, @Valid @RequestBody CountyData countyData, BindingResult result) {
        if (result.hasErrors()) {
            throw new MultiErrorException("Klaida atnaujinant apylinkę " + countyData.getName(), result.getAllErrors());
        }
        return districtService.updateCounty(districtId, countyData, cid);
    }

    @DeleteMapping("/county/{id}")
    public void deleteCounty(@PathVariable Long id) {
        districtService.deleteCounty(id);
    }

}