package voting.handlers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import voting.exception.CandidateMultiErrorsException;
import voting.exception.CandidateMultiErrorsResponse;

/**
 * Created by andrius on 2/6/17.
 */

@ControllerAdvice
public class CandidateMultiExceptionsHandler {

    @ExceptionHandler(CandidateMultiErrorsException.class)
    public ResponseEntity<Object> catchAllFieldExceptions(CandidateMultiErrorsException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        CandidateMultiErrorsResponse mer = new CandidateMultiErrorsResponse(status.value(), ex.getErrors());
        return new ResponseEntity<Object>(mer, new HttpHeaders(), status);
    }
}
