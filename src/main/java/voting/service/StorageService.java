package voting.service;

import com.opencsv.exceptions.CsvException;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import voting.model.CandidateData;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by domas on 1/16/17.
 */
public interface StorageService {

    void store(String fileName, MultipartFile file);

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();

    public List<CandidateData> parseDistrictCandidateDataList(String fileName) throws CsvException, IOException;

}
