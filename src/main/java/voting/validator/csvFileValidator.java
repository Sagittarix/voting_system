package voting.validator;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;
import voting.dto.PartyData;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by andrius on 2/5/17.
 */

@Component
public class csvFileValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return MultipartFile.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MultipartFile multipartFile = (MultipartFile) target;

    }
}


//csv validavimas
// non empty
// lines > 1
//header ar teisingas
// not null