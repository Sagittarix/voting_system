package voting.exception;

import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * Created by andrius on 2/8/17.
 */

public class DTOMultiObjectsErrorsException extends RuntimeException {

    private List<ObjectError> errors;

    public DTOMultiObjectsErrorsException(String message, List<ObjectError> errors) {
        super(message);
        this.errors = errors;
    }

    public List<ObjectError> getErrors() {
        return errors;
    }

    public void setErrors(List<ObjectError> errors) {
        this.errors = errors;
    }
}
