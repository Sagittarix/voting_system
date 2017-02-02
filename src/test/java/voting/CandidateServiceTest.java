package voting;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.core.EntityInformation;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import voting.exception.NotFoundException;
import voting.exception.StorageException;
import voting.model.Candidate;
import voting.repository.CandidateRepository;
import voting.service.CandidateService;
import voting.service.CandidateServiceImpl;

import java.io.Serializable;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by domas on 2/1/17.
 */

@RunWith(SpringRunner.class)
public class CandidateServiceTest {

    CandidateRepository repo = mock(CandidateRepository.class);

    CandidateService service = new CandidateServiceImpl(repo);

    @Test(expected = NotFoundException.class)
    public void gettingCandidateWithNonExistingIdThrowsNotFound() {
        when(repo.findOne(1L)).thenReturn(null);
        service.getCandidate(1L);
    }

    @Test(expected = NotFoundException.class)
    public void gettingCandidateWithNonExistingPersonIdThrowsNotFound() {
        when(repo.findByPersonId("111")).thenReturn(null);
        service.getCandidate("111");
    }

}
