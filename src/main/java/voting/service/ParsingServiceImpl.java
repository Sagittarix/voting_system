package voting.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvException;
import org.springframework.stereotype.Service;
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

    // TODO: refactor - both methods are almost the same, maybe use Strategy DP

    @Override
    public List<CandidateData> parseSingleMandateDistrictCandidateList(File file) throws IOException, CsvException {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        String header = "Vardas,Pavardė,Asmens_kodas,Partija";
        List<CandidateData> candidateDataList = new ArrayList<>();
        int columnCount = header.split(",").length;

        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            String[] line;
            line = reader.readNext();

            if (header.split(",").length != columnCount
                    || !Arrays.equals(line, header.split(","))) {
                throw (new CsvException("No header or header doesn't match"));
            }

            int lineNumber = 2;
            while ((line = reader.readNext()) != null) {
                if (line.length != columnCount) {
                    throw (new CsvException("Invalid data at line " + lineNumber));
                }
                //TODO: add proper validation / exception handling
                try {
                    CandidateData candidateData = new CandidateData();
                    candidateData.setFirstName(line[0]);
                    candidateData.setLastName(line[1]);
                    candidateData.setPersonId(line[2]);
                    candidateData.setPartyName(line[3]);
                    candidateDataList.add(candidateData);
                    Set<ConstraintViolation<CandidateData>> violations = validator.validate(candidateData);
                    if (violations.size() > 0) {
                        throw (new CsvConstraintViolationException("Constaint violation at line " + lineNumber));
                    }
                    lineNumber++;
                } catch (NumberFormatException ex) {
                    throw (new CsvException("Invalid data at line " + lineNumber));
                }
            }
        } catch (IOException e) {
            throw (new IOException("error reading file"));
        }

        return candidateDataList;
    }


    @Override
    public List<CandidateData> parsePartyCandidateList(File file) throws CsvException, IOException {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        String header = "Numeris,Vardas,Pavardė,Asmens_kodas";
        List<CandidateData> candidateDataList = new ArrayList<>();
        int columnCount = header.split(",").length;

        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            String[] line;
            line = reader.readNext();

            if (header.split(",").length != columnCount
                    || !Arrays.equals(line, header.split(","))) {
                throw (new CsvException("No header or header doesn't match"));
            }

            int lineNumber = 2;
            while ((line = reader.readNext()) != null) {
                if (line.length != columnCount) {
                    throw (new CsvException("Invalid data at line " + lineNumber));
                }
                //TODO: add proper validation / exception handling
                try {
                    CandidateData candidateData = new CandidateData();
                    candidateData.setPositionInPartyList(Long.parseLong(line[0]));
                    candidateData.setFirstName(line[1]);
                    candidateData.setLastName(line[2]);
                    candidateData.setPersonId(line[3]);
                    candidateDataList.add(candidateData);
                    Set<ConstraintViolation<CandidateData>> violations = validator.validate(candidateData);
                    if (violations.size() > 0) {
                        throw (new CsvConstraintViolationException("Constaint violation at line " + lineNumber));
                    }
                    lineNumber++;
                } catch (NumberFormatException ex) {
                    throw (new CsvException("Invalid data at line " + lineNumber));
                }
            }
        } catch (IOException e) {
            throw (new IOException("error reading file"));
        }


        return candidateDataList;
    }
}
