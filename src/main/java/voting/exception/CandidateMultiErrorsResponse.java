package voting.exception;

import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by andrius on 2/6/17.
 */
public class CandidateMultiErrorsResponse {

    private int errorCode;
    private List<String> errorsMessages;

    public CandidateMultiErrorsResponse(int errorCode, List<ObjectError> errors) {
        this.errorCode = errorCode;
        this.errorsMessages = this.getMessages(errors);
    }

    private List<String> getMessages(List<ObjectError> errors) {
        return errors.stream()
                .map(e -> e.getDefaultMessage())
                .collect(Collectors.toList());
    }
}
