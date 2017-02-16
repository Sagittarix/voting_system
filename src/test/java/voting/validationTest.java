package voting;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import voting.dto.CandidateData;
import voting.dto.PartyData;
import voting.exception.MultiErrorException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * Created by domas on 2/14/17.
 */
public class validationTest {


    @Test
    public void test1() {
        ValidatorFactory factory;
        Validator validator;
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();



        CandidateData cd = new CandidateData();

        cd.setFirstName("A");
//        cd.setPersonId("");

        Set<ConstraintViolation<CandidateData>> violations = validator.validate(cd);
//        violations.stream().forEach(v -> System.out.println(v.getInvalidValue()));
//        violations.stream().forEach(v -> System.out.println(v.getPropertyPath()));
    }

    @Ignore
    @Test
    public void test2() {
        PartyData partyData = new PartyData();
        partyData.setName("x");

        LocalValidatorFactoryBean vf = new LocalValidatorFactoryBean();
        Errors bindingResult = new BeanPropertyBindingResult(partyData, "partyData");
        ValidationUtils.invokeValidator(vf, partyData, bindingResult);

    }


}
