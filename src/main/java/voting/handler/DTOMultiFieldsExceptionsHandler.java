package voting.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import voting.exception.DTOMultiFieldsErrorsException;
import voting.exception.error_response.DTOMultiFieldsErrorsResponse;

/**
 * Created by andrius on 2/3/17.
 */

@ControllerAdvice
public class DTOMultiFieldsExceptionsHandler {

    @ExceptionHandler(DTOMultiFieldsErrorsException.class)
    public ResponseEntity<Object> catchAllFieldExceptions(DTOMultiFieldsErrorsException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        DTOMultiFieldsErrorsResponse mer = new DTOMultiFieldsErrorsResponse(status.value(), ex.getErrors());
        return new ResponseEntity<Object>(mer, new HttpHeaders(), status);
    }
}
