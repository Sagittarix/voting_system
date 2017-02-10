package voting.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import voting.exception.StorageException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 * Created by domas on 1/16/17.
 */
@Service
public class FileSystemStorageService implements StorageService {

    @Value("${storage.rootLocation}")
    private Path rootLocation;

    @Override
    public Path store(String fileName, MultipartFile file) {
        Path filePath = rootLocation.resolve(fileName);
        try {
            if (!Files.exists(rootLocation)) {
                Files.createDirectory(rootLocation);
            }
            Files.copy(file.getInputStream(), filePath, REPLACE_EXISTING);
            return filePath;
        } catch (IOException e) {
            throw new StorageException("Nepavyko išsaugoti failo " + file.getOriginalFilename(), e);
        }
    }

    @Override
    public Path storeTemporary(MultipartFile file) {
        Path filePath = null;
        try {
            filePath = Files.createTempFile("temp", "csv");
            Files.copy(file.getInputStream(), filePath, REPLACE_EXISTING);
        } catch (IOException e) {
            throw new StorageException("Failed to store file", e);
        }
        return filePath;
    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public void delete(Path filePath) throws IOException {
        Files.deleteIfExists(filePath);
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }


}