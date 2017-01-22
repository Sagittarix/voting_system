package voting.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import voting.exception.StorageException;
import voting.exception.StorageFileNotFoundException;
import voting.dto.CandidateData;

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

    @Value("${storage.rootLocation}")
    private Path rootLocation;

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


}