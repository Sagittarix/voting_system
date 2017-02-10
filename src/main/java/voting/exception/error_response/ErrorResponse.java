package voting.exception.error_response;

import org.springframework.validation.FieldError;

import java.util.List;

/**
 * Created by domas on 1/17/17.
 */
public class ErrorResponse {

    private int errorCode;
    private String message;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
