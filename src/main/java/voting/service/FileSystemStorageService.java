package voting.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import voting.exception.StorageException;
import voting.exception.StorageFileNotFoundException;
import voting.model.CandidateData;

import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by domas on 1/16/17.
 */
@Service
public class FileSystemStorageService implements StorageService {

    private final Path rootLocation;

//    @Autowired
//    public FileSystemStorageService(StorageProperties properties) {
//        this.rootLocation = Paths.get(properties.getLocation());
//    }


    public FileSystemStorageService() {
        this.rootLocation = Paths.get("uploaded-files");
    }

    @Override
    public void store(String fileName, MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Empty file " + file.getOriginalFilename());
            }
            if (!Files.exists(rootLocation)) {
                Files.createDirectory(rootLocation);
            }
            if (Files.exists(rootLocation.resolve(fileName))) {
                Files.delete(rootLocation.resolve(fileName));
            }
            Files.copy(file.getInputStream(), this.rootLocation.resolve(fileName));
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
        }
    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException("Could not read file: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }


    @Override
    public List<CandidateData> parseDistrictCandidateDataList(String fileName) throws CsvException, IOException {
        String header = "Vardas,Pavardė,Asmens_kodas,Partija";
        List<CandidateData> candidateDataList = new ArrayList<>();
        int columnCount = header.split(",").length;

        CSVReader reader = new CSVReader(new FileReader(rootLocation.resolve(fileName).toFile()));
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