package voting.validator;

import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;
import voting.dto.PartyData;
import voting.service.ParsingServiceImpl;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

/**
 * Created by andrius on 2/5/17.
 */

@Component
public class CsvFileValidator implements Validator {

    private ParsingServiceImpl.SingleMandateCandidateParsingStrategy singleMandateCandidateParsingStrategy;
    private ParsingServiceImpl.MultiMandateCandidateParsingStrategy multiMandateCandidateParsingStrategy;

    @Autowired
    public CsvFileValidator(ParsingServiceImpl.SingleMandateCandidateParsingStrategy singleMandateCandidateParsingStrategy,
                            ParsingServiceImpl.MultiMandateCandidateParsingStrategy multiMandateCandidateParsingStrategy) {
        this.singleMandateCandidateParsingStrategy = singleMandateCandidateParsingStrategy;
        this.multiMandateCandidateParsingStrategy = multiMandateCandidateParsingStrategy;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return MultipartFile.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MultipartFile file = (MultipartFile) target;
        int columnCount = 4;                                // HARDCODED - change if headers length changes OR headers become NON EQUALS in LENGTH
        List<String[]> lines = null;

        if (!file.getContentType().contains("text/csv")) {
            errors.reject(HttpStatus.NOT_ACCEPTABLE.toString(),
                    "Spring - Failas turi CSV formato");
            return;
        }

        try (CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            lines = reader.readAll();
        } catch (IOException e) {
            errors.reject(HttpStatus.NOT_FOUND.toString(),
                    "Spring - Klaida nuskaitant failą");
            return;                                                 // method terminator
        }

        if (lines.isEmpty()) {
            errors.reject(HttpStatus.NOT_ACCEPTABLE.toString(),
                    "Spring - Failas tuščias");
            return;                                                 // method terminator
        }

        if (!Arrays.equals(lines.get(0), singleMandateCandidateParsingStrategy.getHeader()) &&
            !Arrays.equals(lines.get(0), multiMandateCandidateParsingStrategy.getHeader())) {
            errors.rejectValue(null,
                    HttpStatus.NOT_ACCEPTABLE.toString(),
                    "Spring - Netaisyklinga antraštė.");
            return;                                                 // method terminator
        } else {
            final List<String[]> finalLines = lines;
            IntStream.range(1, lines.size())
                     .filter(i -> finalLines.get(i).length != columnCount)
                     .forEach(i -> {
                         errors.reject(HttpStatus.EXPECTATION_FAILED.toString(),
                                 "Spring - Duomenų klaida eilutėje " + i);
                     });
        }

        /*errors.rejectValue(null,
                HttpStatus.NOT_ACCEPTABLE.toString(),
                "Spring - TEST ERROR");*/
    }
}