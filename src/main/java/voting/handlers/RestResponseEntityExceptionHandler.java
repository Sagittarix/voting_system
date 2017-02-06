package voting.handlers;

import com.opencsv.exceptions.CsvException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import voting.exception.ErrorResponse;
import voting.exception.NotFoundException;
import voting.exception.StorageException;
import voting.exception.StorageFileNotFoundException;

import java.io.IOException;

/**
 * Created by domas on 1/17/17.
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler {

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
        } else if (ex instanceof NotFoundException) {
            status = HttpStatus.NOT_FOUND;
        } else if (ex instanceof IllegalArgumentException) {
            status = HttpStatus.CONFLICT;
        }
        ErrorResponse body = new ErrorResponse();
        body.setErrorCode(status.value());
        body.setMessage(ex.getMessage());
        return new ResponseEntity(body, new HttpHeaders(), status);
    }


    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleException(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorResponse body = new ErrorResponse();
        body.setErrorCode(status.value());
        body.setMessage(ex.getMessage());
        return new ResponseEntity(body, new HttpHeaders(), status);
    }



}