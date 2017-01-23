package voting.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

/**
 * Created by domas on 1/16/17.
 */
public interface StorageService {

    void store(String fileName, MultipartFile file);

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();

}
