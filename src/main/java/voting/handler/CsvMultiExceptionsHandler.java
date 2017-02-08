package voting.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import voting.exception.CsvMultiErrorsException;
import voting.exception.error_response.CsvMultiErrorsResponse;

/**
 * Created by andrius on 2/6/17.
 */

@ControllerAdvice
public class CsvMultiExceptionsHandler {

    @ExceptionHandler(CsvMultiErrorsException.class)
    public ResponseEntity<Object> catchAllFieldExceptions(CsvMultiErrorsException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        CsvMultiErrorsResponse mer = new CsvMultiErrorsResponse(status.value(), ex.getErrors());
        return new ResponseEntity<Object>(mer, new HttpHeaders(), status);
    }
}
