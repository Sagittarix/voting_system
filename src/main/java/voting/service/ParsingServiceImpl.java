package voting.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.validation.*;
import voting.dto.CandidateData;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Created by domas on 1/21/17.
 */
@Service
public class ParsingServiceImpl implements ParsingService {

    ValidatorFactory factory;
    Validator validator;

    public ParsingServiceImpl() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Override
    public List<CandidateData> parseSingleMandateCandidateList(File file) throws IOException, CsvException {
        CandidateParsingStrategy strategy = new SingleMandateCandidateParsingStrategy();
        return parseCandidateList(strategy, file);
    }

    @Override
    public List<CandidateData> parseMultiMandateCandidateList(File file) throws IOException, CsvException {
        CandidateParsingStrategy strategy = new MultiMandateCandidateParsingStrategy();
        return parseCandidateList(strategy, file);
    }

    private List<CandidateData> parseCandidateList(CandidateParsingStrategy strategy, File file) throws CsvException, IOException {

        String[] expectedHeader = strategy.getHeader();

        int expectedColumnCount = expectedHeader.length;
        List<CandidateData> candidateDataList = new ArrayList<>();
        Errors errors = null;

        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            String[] header = reader.readNext();

            if (header == null || !Arrays.equals(header, expectedHeader)) {
                throw new CsvMultiErrorsException(
                        "CSV header error",
                        new ArrayList<ObjectError>() {{ add(
                                new ObjectError(
                                        "file",
                                        "Spring - Bloga failo vidinė antraštė. Turi būti - " +
                                                String.join(",", expectedHeader)));
                        }}
                );
            }

            String[] line;
            int lineNumber = 2; // line 1 was header
            errors = new BeanPropertyBindingResult(file, "file");

            while ((line = reader.readNext()) != null) {
                if (line.length != expectedColumnCount) {
                    errors.reject(HttpStatus.CONFLICT.toString(), "Spring - Duomenų klaida eilutėje - " + lineNumber);
                }
                try {
                    CandidateData candidateData = strategy.parseCandidateData(line);
                    strategy.validate(candidateData, lineNumber);
                    candidateDataList.add(candidateData);
                } catch (NumberFormatException | CsvException ex) {
                    errors.reject(HttpStatus.CONFLICT.toString(), "Spring - Duomenų klaida eilutėje - " + lineNumber);
                }
                lineNumber++;
            }
        } catch (IOException e) {
            throw new CsvMultiErrorsException(
                    "Cannot read CSV file",
                    new ArrayList<ObjectError>() {{ add(
                            new ObjectError(
                                    "file",
                                    "Spring - Klaida skaitant CSV failą"));
                    }}
            );
        }
        if (errors.getAllErrors().size() > 0) throw new DTOMultiObjectsErrorsException("CSV file errors in multiple lines", errors.getAllErrors());
        return candidateDataList;
    }


    private abstract class CandidateParsingStrategy {

        private String[] header;

        public CandidateParsingStrategy(String[] header) {
            this.header = header;
        }

        public String[] getHeader() {
            return header;
        }

        abstract CandidateData parseCandidateData(String[] line);

        abstract void validate(CandidateData candidateData, int lineNumber) throws CsvException;
    }

    @Component
    public class SingleMandateCandidateParsingStrategy extends CandidateParsingStrategy {

        public SingleMandateCandidateParsingStrategy() {
            super("Vardas,Pavardė,Asmens_kodas,Partija".split(","));
        }

        @Override
        public CandidateData parseCandidateData(String[] line) {
            CandidateData candidateData = new CandidateData();
            candidateData.setFirstName(line[0]);
            candidateData.setLastName(line[1]);
            candidateData.setPersonId(line[2]);
            candidateData.setPartyName(line[3]);
            return candidateData;
        }

        @Override
        void validate(CandidateData candidateData, int lineNumber) throws CsvException {
            Set<ConstraintViolation<CandidateData>> violations = validator.validate(candidateData);
            violations.forEach(System.out::println);    // TODO

            if (violations.size() > 0) {
                throw (new CsvConstraintViolationException(String.format("Invalid data at line %d: %d constraint(s) violated",
                        lineNumber, violations.size())));
            }
        }
    }

    @Component
    public class MultiMandateCandidateParsingStrategy extends CandidateParsingStrategy {

        public MultiMandateCandidateParsingStrategy() {
            super("Numeris,Vardas,Pavardė,Asmens_kodas".split(","));
        }

        @Override
        public CandidateData parseCandidateData(String[] line) {
            CandidateData candidateData = new CandidateData();
            candidateData.setPositionInPartyList(Long.parseLong(line[0]));
            candidateData.setFirstName(line[1]);
            candidateData.setLastName(line[2]);
            candidateData.setPersonId(line[3]);
            return candidateData;
        }

        @Override
        void validate(CandidateData candidateData, int lineNumber) throws CsvException {
            Set<ConstraintViolation<CandidateData>> violations = validator.validate(candidateData);
            if (violations.size() > 0) {
                throw (new CsvConstraintViolationException(String.format("Invalid data at line %d: %d constraint(s) violated",
                        lineNumber, violations.size())));
            }
            int expectedPositionInPartyList = lineNumber - 1;
            if (candidateData.getPositionInPartyList() != expectedPositionInPartyList) {
                throw (new CsvException("Invalid data at line " + lineNumber + ": incontinuous position in party list"));
            }
        }
    }
}
