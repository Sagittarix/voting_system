package voting.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import voting.exception.CountyResultFieldsErrorsException;
import voting.exception.DTOMultiFieldsErrorsException;
import voting.exception.error_response.CountyResultMultiErrorsResponse;
import voting.exception.error_response.DTOMultiFieldsErrorsResponse;

/**
 * Created by andrius on 2/3/17.
 */

@ControllerAdvice
public class CountyResultMultiExceptionsHandler {

    @ExceptionHandler(CountyResultFieldsErrorsException.class)
    public ResponseEntity<Object> catchAllFieldExceptions(CountyResultFieldsErrorsException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        CountyResultMultiErrorsResponse mer = new CountyResultMultiErrorsResponse(status.value(), ex.getErrors());
        return new ResponseEntity<Object>(mer, new HttpHeaders(), status);
    }
}
