package voting.controller;

import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import voting.dto.candidate.CandidateDTO;
import voting.dto.county.CountyDTO;
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
@RequestMapping(path = "/api/county")
public class CountyController {

    private DistrictService districtService;

    @Autowired
    public CountyController(DistrictService districtService) {
        this.districtService = districtService;
    }

    @GetMapping()
    public List<CountyDTO> getCounties() {
        return districtService.getCounties().stream().map(CountyDTO::new).collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public CountyDTO getCounty(@PathVariable Long id) {
        return new CountyDTO(districtService.getCounty(id));
    }

    @DeleteMapping("{id}")
    public void deleteCounty(@PathVariable Long id) {
        districtService.deleteCounty(id);
    }

}