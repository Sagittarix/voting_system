package voting.controller;

import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import voting.dto.CandidateData;
import voting.dto.CandidateListData;
import voting.dto.DistrictData;
import voting.dto.DistrictRepresentation;
import voting.exception.ErrorResponse;
import voting.exception.StorageException;
import voting.exception.StorageFileNotFoundException;
import voting.service.DistrictService;
import voting.service.ParsingService;
import voting.service.StorageService;

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
    private StorageService storageService;
    private ParsingService parsingService;

    @Autowired
    public DistrictController(DistrictService districtService, StorageService storageService, ParsingService parsingService) {
        this.districtService = districtService;
        this.storageService = storageService;
        this.parsingService = parsingService;
    }


    @GetMapping
    public List<DistrictRepresentation> getDistricts() {
        return districtService.getDistricts().stream().map(d -> new DistrictRepresentation(d)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public DistrictRepresentation getDistrict(@PathVariable Long id) {
        return new DistrictRepresentation(districtService.getDistrict(id));
    }

    @PostMapping
    public DistrictRepresentation addNewDistrict(@Valid @RequestBody DistrictData districtData) {
        return new DistrictRepresentation(districtService.addNewDistrict(districtData));
    }

    @DeleteMapping("/{id}")
    public void deleteDistrict(@PathVariable Long id) {
        districtService.deleteDistrict(id);
    }

    @PostMapping("/{id}/candidates2")
    public DistrictRepresentation addCandidateList(@PathVariable Long id, @Valid @RequestBody CandidateListData candidateListData) {
        return new DistrictRepresentation(districtService.addCandidateList(id, candidateListData.getCandidateListData()));
    }

    @DeleteMapping("/{id}/candidates")
    public void deleteCandidateList(@PathVariable Long id) {
        districtService.deleteCandidateList(id);
    }

    @PostMapping(value = "/{id}/candidates", consumes = "multipart/form-data")
    public DistrictRepresentation handleFileUpload(@PathVariable Long id, @RequestParam(name = "file") MultipartFile file)
            throws IOException, CsvException {
        String fileName = String.format("district_%d.csv", id);
        storageService.store(fileName, file);
        Resource fileResource = storageService.loadAsResource(fileName);
        List<CandidateData> candidateListData = (parsingService.parseSingleMandateCandidateList(fileResource.getFile()));
        return new DistrictRepresentation(districtService.addCandidateList(id, candidateListData));
    }

    // TODO: ideti exceptionus notFound atveju

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