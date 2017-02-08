package voting.exception;

import org.springframework.validation.FieldError;

import java.util.List;

/**
 * Created by andrius on 2/3/17.
 */

public class DTOMultiFieldsErrorsException extends RuntimeException {

    private List<FieldError> errors;

    public DTOMultiFieldsErrorsException(String message, List<FieldError> errors) {
        super(message);
        this.errors = errors;
    }

    public List<FieldError> getErrors() {
        return errors;
    }

    public void setErrors(List<FieldError> errors) {
        this.errors = errors;
    }
}
