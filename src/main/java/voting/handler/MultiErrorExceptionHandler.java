package voting.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import voting.exception.MultiErrorException;
import voting.exception.error_response.MultiErrorResponse;

/**
 * Created by domas on 2/13/17.
 */
@ControllerAdvice
public class MultiErrorExceptionHandler {

    @ExceptionHandler(MultiErrorException.class)
    public ResponseEntity<Object> catchAllFieldExceptions(MultiErrorException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        MultiErrorResponse response = new MultiErrorResponse(status.value(), ex.getErrors());
        return new ResponseEntity<Object>(response, new HttpHeaders(), status);
    }
}
