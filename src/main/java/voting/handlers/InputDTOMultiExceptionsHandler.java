package voting.handlers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import voting.exception.InputDTOMultiErrorsException;
import voting.exception.InputDTOMultiErrorsResponce;

/**
 * Created by andrius on 2/3/17.
 */

@ControllerAdvice
public class InputDTOMultiExceptionsHandler {

    @ExceptionHandler(InputDTOMultiErrorsException.class)
    public ResponseEntity<Object> catchAllFieldExceptions(InputDTOMultiErrorsException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        InputDTOMultiErrorsResponce mer = new InputDTOMultiErrorsResponce(status.value(), ex.getErrors());
        return new ResponseEntity<Object>(mer, new HttpHeaders(), status);
    }
}
