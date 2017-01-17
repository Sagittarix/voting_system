package voting.controller;

import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import voting.exception.ErrorResponse;
import voting.exception.StorageException;
import voting.exception.StorageFileNotFoundException;
import voting.model.CandidateData;
import voting.model.District;
import voting.model.DistrictCandidatesData;
import voting.model.DistrictData;
import voting.service.DistrictService;
import voting.service.StorageService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * Created by domas on 1/10/17.
 */

@RestController
@RequestMapping(path = "/api/district")
public class DistrictController {

    private DistrictService districtService;
    private StorageService storageService;

    @Autowired
    public DistrictController(DistrictService districtService, StorageService storageService) {
        this.districtService = districtService;
        this.storageService = storageService;
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
    public District addNewDistrict(@Valid @RequestBody DistrictData districtData) {
        return districtService.addNewDistrict(districtData);
    }

    @DeleteMapping("/{id}")
    public void deleteDistrict(@PathVariable Long id) {
        districtService.deleteDistrict(id);
    }

    @PostMapping("/{id}/candidates")
    public District addCandidateList(@Valid @RequestBody DistrictCandidatesData districtCandidatesData) {
        return districtService.addCandidateList(districtCandidatesData);
    }

    @PostMapping("/{id}/candidates/upload")
    public District handleFileUpload(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws IOException, CsvException {
        String fileName = "district_" + id;
        storageService.store(fileName, file);
        DistrictCandidatesData districtCandidatesData = new DistrictCandidatesData();
        districtCandidatesData.setDistrictId(id);
        districtCandidatesData.setCandidatesData(storageService.parseDistrictCandidateDataList(fileName));
        return districtService.addCandidateList(districtCandidatesData);
    }

    @ExceptionHandler({CsvException.class, StorageException.class, IOException.class})
    protected ResponseEntity<Object> handleSomeException(Exception ex, WebRequest request) {
        HttpStatus status = null;
        if (ex instanceof CsvException) {
            status = HttpStatus.BAD_REQUEST;
        } else if (ex instanceof StorageFileNotFoundException) {
            status = HttpStatus.NOT_FOUND;
        } else if (ex instanceof StorageException) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        } else if (ex instanceof IOException) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        ErrorResponse body = new ErrorResponse();
        body.setErrorCode(status.value());
        body.setMessage(ex.getMessage());
        return new ResponseEntity(body, new HttpHeaders(), status);
    }


}
