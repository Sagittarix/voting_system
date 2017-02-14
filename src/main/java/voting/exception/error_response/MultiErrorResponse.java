package voting.exception.error_response;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by domas on 2/13/17.
 */
public class MultiErrorResponse {

    private int errorCode;
    private List<String> messages;

    public MultiErrorResponse(int errorCode, List<ObjectError> errors) {
        this.errorCode = errorCode;
        this.messages = errors.stream()
                                .map(e -> e.getDefaultMessage())
                                .collect(Collectors.toList());
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

}
