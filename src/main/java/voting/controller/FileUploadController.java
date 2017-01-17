package voting.controller;

import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import voting.exception.ErrorResponse;
import voting.exception.StorageException;
import voting.exception.StorageFileNotFoundException;
import voting.model.CandidateData;
import voting.service.CandidateService;
import voting.service.StorageService;

import java.io.IOException;
import java.util.List;

/**
 * Created by domas on 1/16/17.
 */
@RestController
public class FileUploadController {

    private final StorageService storageService;
    private final CandidateService candidateService;

    @Autowired
    public FileUploadController(StorageService storageService, CandidateService candidateService) {
        this.storageService = storageService;
        this.candidateService = candidateService;
    }


    @PostMapping("/upload")
    public List<CandidateData> handleFileUpload(@RequestParam("partyId") Long id, @RequestParam("file") MultipartFile file) throws IOException, CsvException{
        storageService.store("party_" + id, file);
        List<CandidateData> candidateDataList;
        candidateDataList = storageService.parseDistrictCandidateDataList("party_" + id);
        return candidateDataList;
    }

    @ExceptionHandler({CsvException.class, StorageException.class, IOException.class})
    protected ResponseEntity<Object> handleSomeException(Exception ex, WebRequest request) {
        HttpStatus status = null;
        if(ex instanceof CsvException) {
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
