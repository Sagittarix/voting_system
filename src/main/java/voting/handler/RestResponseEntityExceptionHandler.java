package voting.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import voting.exception.ErrorResponse;

/**
 * Created by domas on 1/17/17.
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleException(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorResponse body = new ErrorResponse();
        body.setErrorCode(status.value());
        body.setMessage(ex.getMessage());
        return new ResponseEntity(body, new HttpHeaders(), status);
    }
}