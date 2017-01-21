package voting.controller;

import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import voting.dto.CandidateData;
import voting.dto.DistrictData;
import voting.exception.ErrorResponse;
import voting.exception.StorageException;
import voting.exception.StorageFileNotFoundException;
import voting.model.District;
import voting.service.DistrictService;
import voting.service.StorageService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Arrays;
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
    public District addCandidateList(@PathVariable Long id, @Valid @RequestBody CandidateData[] candidateListData) {
        return districtService.addCandidateList(id, Arrays.asList(candidateListData));
    }

    @DeleteMapping("/{id}/candidates")
    public void deleteCandidateList(@PathVariable Long id) {
        districtService.deleteCandidateList(id);
    }

    @PostMapping(value = "/{id}/candidates/upload", consumes = "multipart/form-data")
    public District handleFileUpload(@PathVariable Long id, @RequestParam(name = "file") MultipartFile file)
            throws IOException, CsvException {
        String fileName = "district_" + id;
        storageService.store(fileName, file);
        List<CandidateData> candidateListData = (storageService.parseDistrictCandidateDataList(fileName));
        return districtService.addCandidateList(id, candidateListData);
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
