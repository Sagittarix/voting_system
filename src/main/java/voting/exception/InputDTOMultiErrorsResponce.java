package voting.exception;

import org.springframework.validation.FieldError;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by andrius on 2/5/17.
 */

public class InputDTOMultiErrorsResponce {
    private int errorCode;
    private List<String> errorsMessages;

    public InputDTOMultiErrorsResponce(int errorCode, List<FieldError> errors) {
        this.errorCode = errorCode;
        this.errorsMessages = this.getMessages(errors);
    }

    private List<String> getMessages(List<FieldError> errors) {
        return errors.stream()
                     .map(e -> e.getDefaultMessage())
                     .collect(Collectors.toList());
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public List<String> getErrorsMessages() {
        return errorsMessages;
    }

    public void setErrorsMessages(List<String> errorsMessages) {
        this.errorsMessages = errorsMessages;
    }
}
