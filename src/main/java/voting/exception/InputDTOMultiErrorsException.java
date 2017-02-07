package voting.exception;

import org.springframework.validation.FieldError;

import java.util.List;

/**
 * Created by andrius on 2/3/17.
 */
public class InputDTOMultiErrorsException extends RuntimeException {

    private List<FieldError> errors;

    public InputDTOMultiErrorsException(String message, List<FieldError> errors) {
        super(message);
        this.errors = errors;
    }

    public InputDTOMultiErrorsException(String message, Throwable cause, List<FieldError> errors) {
        super(message, cause);
        this.errors = errors;
    }

    public List<FieldError> getErrors() {
        return errors;
    }

    public void setErrors(List<FieldError> errors) {
        this.errors = errors;
    }
}
