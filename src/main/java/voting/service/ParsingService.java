package voting.service;

import com.opencsv.exceptions.CsvException;
import voting.dto.CandidateData;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by domas on 1/21/17.
 */
public interface ParsingService {

    public List<CandidateData> parseSingleMandateDistrictCandidateList(File file) throws CsvException, IOException;

    public List<CandidateData> parsePartyCandidateList(File file) throws CsvException, IOException;
}
