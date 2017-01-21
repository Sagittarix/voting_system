package voting.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.stereotype.Service;
import voting.dto.CandidateData;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by domas on 1/21/17.
 */
@Service
public class ParsingServiceImpl implements ParsingService {


    @Override
    public List<CandidateData> parseDistrictCandidateDataList(File file) throws CsvException, IOException {
        String header = "Vardas,Pavardė,Asmens_kodas,Partija";
        List<CandidateData> candidateDataList = new ArrayList<>();
        int columnCount = header.split(",").length;

        CSVReader reader = new CSVReader(new FileReader(file));
        String[] line;

        line = reader.readNext();

        if (header.split(",").length != columnCount
                || !Arrays.equals(line, header.split(","))) {
            throw (new CsvException("No header or header doesn't match"));
        }

        int lineNumber = 1;
        while ((line = reader.readNext()) != null) {
            if (line.length != columnCount) {
                throw (new CsvException());
            }
            //TODO: add exception handling
            CandidateData candidateData = new CandidateData();
            candidateData.setFirstName(line[0]);
            candidateData.setLastName(line[1]);
            candidateData.setPersonId(line[2]);
            candidateData.setPartyName(line[3]);
            candidateDataList.add(candidateData);
            lineNumber++;
        }
        return candidateDataList;
    }
}
