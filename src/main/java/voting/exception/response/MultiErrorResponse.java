package voting.exception.response;

import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by domas on 2/13/17.
 */
public class MultiErrorResponse {

    private int errorCode;
    private List<String> errorsMessages;

    public MultiErrorResponse(int errorCode, List<ObjectError> errors) {
        this.errorCode = errorCode;
        this.errorsMessages = errors.stream()
                                .map(e -> e.getDefaultMessage())
                                .collect(Collectors.toList());
    }

    public MultiErrorResponse(int errorCode, String message) {
        this.errorCode = errorCode;
        this.errorsMessages = new ArrayList<String>(Arrays.asList(message));
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public List<String> getMessages() {
        return errorsMessages;
    }

    public void setMessages(List<String> errorsMessages) {
        this.errorsMessages = errorsMessages;
    }

}
