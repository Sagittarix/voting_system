package voting.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
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
        String[] correctHeader = strategy.getHeader();
        int columnCount = correctHeader.length;
        List<CandidateData> candidateDataList = new ArrayList<>();


        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            String[] header = reader.readNext();

            if (header == null
                    || header.length != columnCount
                    || !Arrays.equals(header, correctHeader)) {
                throw (new CsvException("Incorrect or no header! CSV header should be - " + String.join(",", correctHeader)));
            }

            String[] line;
            int lineNumber = 2; // line 1 was header

            while ((line = reader.readNext()) != null) {
                if (line.length != columnCount) {
                    throw (new CsvException("Invalid data at line " + lineNumber));
                }

                //TODO: add proper validation / exception handling

                try {
                    CandidateData candidateData = strategy.createCandidateData(line);
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


    private abstract class CandidateParsingStrategy {

        private String[] header;

        public CandidateParsingStrategy(String[] header) {
            this.header = header;
        }

        public String[] getHeader() {
            return header;
        }

        abstract CandidateData createCandidateData(String[] line);
    }


    private class SingleMandateCandidateParsingStrategy extends CandidateParsingStrategy {

        public SingleMandateCandidateParsingStrategy() {
            super("Vardas,Pavardė,Asmens_kodas,Partija".split(","));
        }

        @Override
        public CandidateData createCandidateData(String[] line) {
            CandidateData candidateData = new CandidateData();
            candidateData.setFirstName(line[0]);
            candidateData.setLastName(line[1]);
            candidateData.setPersonId(line[2]);
            candidateData.setPartyName(line[3]);
            return candidateData;
        }
    }

    private class MultiMandateCandidateParsingStrategy extends CandidateParsingStrategy {

        public MultiMandateCandidateParsingStrategy() {
            super("Numeris,Vardas,Pavardė,Asmens_kodas".split(","));
        }

        @Override
        public CandidateData createCandidateData(String[] line) {
            CandidateData candidateData = new CandidateData();
            candidateData.setPositionInPartyList(Long.parseLong(line[0]));
            candidateData.setFirstName(line[1]);
            candidateData.setLastName(line[2]);
            candidateData.setPersonId(line[3]);
            return candidateData;
        }
    }
}
