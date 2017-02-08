package voting.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import voting.exception.DTOMultiFieldsErrorsException;
import voting.exception.DTOMultiObjectsErrorsException;
import voting.exception.error_response.DTOMultiFieldsErrorsResponse;
import voting.exception.error_response.DTOMultiObjectsErrorsResponse;

/**
 * Created by andrius on 2/3/17.
 */

@ControllerAdvice
public class DTOMultiObjectsExceptionsHandler {

    @ExceptionHandler(DTOMultiObjectsErrorsException.class)
    public ResponseEntity<Object> catchAllFieldExceptions(DTOMultiObjectsErrorsException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        DTOMultiObjectsErrorsResponse mer = new DTOMultiObjectsErrorsResponse(status.value(), ex.getErrors());
        return new ResponseEntity<Object>(mer, new HttpHeaders(), status);
    }
}
