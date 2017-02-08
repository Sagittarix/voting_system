package voting.exception.error_response;

import org.springframework.validation.ObjectError;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by andrius on 2/6/17.
 */
public class CsvMultiErrorsResponse {

    private int errorCode;
    private List<String> errorsMessages;

    public CsvMultiErrorsResponse(int errorCode, List<ObjectError> errors) {
        this.errorCode = errorCode;
        this.errorsMessages = this.getMessages(errors);
    }

    private List<String> getMessages(List<ObjectError> errors) {
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
