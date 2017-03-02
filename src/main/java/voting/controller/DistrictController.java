package voting.controller;

import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import voting.dto.candidate.CandidateDTO;
import voting.dto.county.CountyData;
import voting.dto.district.DistrictDTO;
import voting.dto.district.DistrictData;
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
    public List<DistrictDTO> getDistricts() {
        return districtService.getDistricts().stream().map(DistrictDTO::new).collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public DistrictDTO getDistrict(@PathVariable Long id) {
        District district = districtService.getDistrict(id);
        return new DistrictDTO(district);
    }

    @PostMapping
    public DistrictDTO addNewDistrict(@Valid @RequestBody DistrictData districtData, BindingResult result) {
        if (result.hasErrors()) {
            throw new MultiErrorException("Klaida registruojant apygardą " + districtData.getName(), result.getAllErrors());
        }
        return new DistrictDTO(districtService.addNewDistrict(districtData));
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
    public DistrictDTO setCandidateList(@PathVariable Long id, @RequestParam(name = "file") MultipartFile file)
            throws IOException, CsvException {
        return new DistrictDTO(districtService.setCandidateList(id, file));
    }

    @GetMapping("/{id}/candidates")
    public List<CandidateDTO> getCandidateList(@PathVariable Long id) {
        return districtService.getCandidateList(id).stream()
                .map(CandidateDTO::new)
                .collect(Collectors.toList());
    }

    @PostMapping("/{id}/add-county")
    public DistrictDTO addCounty(@PathVariable("id") Long districtId, @Valid @RequestBody CountyData countyData, BindingResult result) {
        if (result.hasErrors()) {
            throw new MultiErrorException("Klaida registruojant apylinkę " + countyData.getName(), result.getAllErrors());
        }
        return new DistrictDTO(districtService.addCounty(districtId, countyData));
    }

    @DeleteMapping("/county/{id}")
    public void deleteCounty(@PathVariable Long id) {
        districtService.deleteCounty(id);
    }

}