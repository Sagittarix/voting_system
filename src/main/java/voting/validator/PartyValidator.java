package voting.validator;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import voting.dto.PartyData;
import voting.model.Party;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by andrius on 2/5/17.
 */

@Component
public class PartyValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return PartyData.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PartyData partyData = (PartyData) target;
        String name = partyData.getName();
        //Pattern nameRegex = Pattern.compile("/^([a-zA-ZąčęėįšųūžĄČĘĖĮŠŲŪŽ\\s][^qQwWxX0-9]*)$/");
        Pattern nameRegex = Pattern.compile("/^([a-zA-Z][^qQwWxX]*)$/");
        Matcher matcher = nameRegex.matcher(name);

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                                            "name",
                                                  HttpStatus.BAD_REQUEST.toString(),
                                    "Spring - Pavadinimas negali būti tuščias");
        if (name.length() < 6) errors.rejectValue("name",
                                                     HttpStatus.BAD_REQUEST.toString(),
                                                 "Spring - Pavadinimas nuo 6 iki 40 simbolių");
        /*if (!matcher.matches()) errors.rejectValue("name",
                                                      HttpStatus.BAD_REQUEST.toString(),
                                                 "Spring - Netinkamas pavadinimo formatas");*/
    }
}
