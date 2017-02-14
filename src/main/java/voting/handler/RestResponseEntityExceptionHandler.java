package voting.handler;

import com.opencsv.exceptions.CsvException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import voting.exception.NotFoundException;
import voting.exception.StorageException;
import voting.exception.StorageFileNotFoundException;
import voting.exception.response.ErrorResponse;

import java.io.IOException;

/**
 * Created by domas on 1/17/17.
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler {



}