package voting;

import com.opencsv.exceptions.CsvException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import voting.dto.CandidateData;
import voting.dto.CountyData;
import voting.dto.DistrictData;
import voting.exception.NotFoundException;
import voting.model.District;
import voting.service.CandidateService;
import voting.service.DistrictService;
import voting.service.ParsingService;
import voting.service.StorageService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by domas on 2/5/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class DistrictServiceIntegrationTest {


    private static Path tmpFilePath;
    private static CandidateData candidate1;
    private static CandidateData candidate2;
    private static CandidateData candidate3;
    private static CountyData county1;
    private static CountyData county2;
    private static CountyData countyWithDuplicateName;

    private DistrictData districtData;
    private List<CountyData> countyDataList;
    private List<CandidateData> candidateDataList;
    private MockMultipartFile multiPartFile = new MockMultipartFile("file.csv", new byte[] {});

    @MockBean
    private ParsingService parsingService;
    @MockBean
    private StorageService storageService;

    @Autowired
    private CandidateService candidateService;

    @InjectMocks
    @Autowired
    private DistrictService sut;


    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @BeforeClass
    public static void beforeClassSetup() throws IOException {
        tmpFilePath = Files.createTempFile("file", "tmp");
        candidate1 = createCandidateData("Petras", "Petraitis", "55500055501", "Išsikėlęs pats");
        candidate2 = createCandidateData("Jonas", "Jonaitis", "55500055502", "Partija 1");
        candidate3 = createCandidateData("Trecias", "Treciasis", "55500055503", "Išsikėlęs pats");
        county1 = createCountyData("Apylinkė 1", 3000L);
        county2 = createCountyData("Apylinkė 2", 4000L);
        countyWithDuplicateName = createCountyData("Apylinkė 1", 5000L);
    }

    @Before
    public void beforeTestSetup() throws IOException, CsvException {
        districtData = new DistrictData();
        districtData.setName("APYGARDA");
        districtData.setCountiesData(Arrays.asList(county1, county2));

        when(storageService.store(any(), any())).thenReturn(tmpFilePath);
        when(storageService.storeTemporary(any())).thenReturn(tmpFilePath);
    }


    @Test(expected = NotFoundException.class)
    public void gettingNonExistingDistrictByIdShouldThrowNotFound() {
        //Sanity check
        assertThat(sut.getDistricts().size(), is(0));

        //Verify
        sut.getDistrict(1L);
    }

    @Test(expected = NotFoundException.class)
    public void gettingNonExistingDistrictByNameShouldThrowNotFound() {
        //Sanity check
        assertThat(sut.getDistricts().size(), is(0));

        //Verify
        sut.getDistrict("aaa");
    }


    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void savesDistrictCorrectly() throws IOException, CsvException {
        //Setup

        //Exercise
        District district = sut.addNewDistrict(districtData);

        //Verify
        assertThat(district.getName(), is("APYGARDA"));
        assertThat(district.getCounties().size(), is(2));
        assertThat(district.getCounties().get(0).getName(), is("Apylinkė 1"));
        assertThat(district.getCounties().get(0).getDistrict().getId(), is(district.getId()));
        assertThat(district.getCounties().get(1).getName(), is("Apylinkė 2"));
        assertThat(district.getCounties().get(1).getDistrict().getId(), is(district.getId()));
    }


    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void savingDistrictWithDuplicatingNameThrowsIllegalArgument() {
        //Setup
        sut.addNewDistrict(districtData);

        //Exercise
        //Verify
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Apygarda \"APYGARDA\" jau egzistuoja");
        District district2 = sut.addNewDistrict(districtData);
    }


    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void shouldntSaveNewDistrictIfItHasCountiesWithDuplicatingNames(){

        //TODO: fix, kad veiktu

        //Setup
        countyDataList = Arrays.asList(county1, countyWithDuplicateName);
        districtData.setCountiesData(countyDataList);

        //Verify
        thrown.expect(IllegalArgumentException.class);
        District district = sut.addNewDistrict(districtData);
        thrown.expect(NotFoundException.class);
        sut.getDistrict("APYGARDA");
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void shouldSetAndUpdateCandidateListCorrectly() throws IOException, CsvException {

        //Setup
        District savedDistrict = sut.addNewDistrict(districtData);

        candidateDataList = Arrays.asList(candidate1, candidate2);
        when(parsingService.parseSingleMandateCandidateList(any())).thenReturn(candidateDataList);

        //Exercise
        savedDistrict = sut.setCandidateList(savedDistrict.getId(), multiPartFile);

        //Verify
        assertThat(savedDistrict.getCandidates().size(), is(2));
        assertThat(savedDistrict.getCandidates().get(0).getPersonId(), is("55500055501"));
        assertThat(savedDistrict.getCandidates().get(1).getPersonId(), is("55500055502"));
        assertThat(candidateService.exists("55500055501"), is(true));
        assertThat(candidateService.exists("55500055502"), is(true));


        //Setup
        candidateDataList = Arrays.asList(candidate3);
        when(parsingService.parseSingleMandateCandidateList(any())).thenReturn(candidateDataList);

        //Exercise
        District updatedDistrict = sut.setCandidateList(savedDistrict.getId(), multiPartFile);

        //Verify
        assertThat(updatedDistrict.getCandidates().size(), is(1));
        assertThat(updatedDistrict.getCandidates().get(0).getPersonId(), is("55500055503"));

        assertThat(candidateService.exists("55500055501"), is(false));
        assertThat(candidateService.exists("55500055502"), is(false));
//        assertThat(candidateService.getCandidate("55500055501").getDistrict(), is(equalTo(null)));

    }

    @Test
    @Transactional
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void shouldDeleteCandidateListCorrectly() throws IOException, CsvException {

        //Setup
        District savedDistrict = sut.addNewDistrict(districtData);

        candidateDataList = Arrays.asList(candidate1, candidate2);
        when(parsingService.parseSingleMandateCandidateList(any())).thenReturn(candidateDataList);
        savedDistrict = sut.setCandidateList(savedDistrict.getId(), multiPartFile);

        //sanity check
        assertThat(savedDistrict.getCandidates().size(), is(2));

        //Exercise
        sut.deleteCandidateList(savedDistrict.getId());
        District updatedDistrict = sut.getDistrict(savedDistrict.getId());

        //Verify
        assertThat(candidateService.exists("55500055501"), is(false));
        assertThat(candidateService.exists("55500055502"), is(false));
    }


    @Test
    @Transactional
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void removingCandidateListShouldDeleteOrphanCandidates() throws IOException, CsvException {

        //Setup
        District savedDistrict = sut.addNewDistrict(districtData);

        candidateDataList = Arrays.asList(candidate1);
        when(parsingService.parseSingleMandateCandidateList(any())).thenReturn(candidateDataList);
        sut.setCandidateList(savedDistrict.getId(), multiPartFile);

        //sanity check
        assertThat(candidateService.exists("55500055501"), is(true));

        //Exercise
        sut.deleteCandidateList(savedDistrict.getId());

        //Verify
        assertThat(candidateService.exists("55500055501"), is(false));
    }


    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void deletesDistrictCorrectly() throws IOException, CsvException {

        //Setup
        District savedDistrict = sut.addNewDistrict(districtData);

        candidateDataList = Arrays.asList(candidate1, candidate2);
        when(parsingService.parseSingleMandateCandidateList(any())).thenReturn(candidateDataList);
        savedDistrict = sut.setCandidateList(savedDistrict.getId(), multiPartFile);

        //sanity check
        assertThat(savedDistrict.getCandidates().size(), is(2));
        assertThat(candidateService.exists("55500055501"), is(true));
        assertThat(candidateService.exists("55500055502"), is(true));


        //Exercise
        sut.deleteDistrict(savedDistrict.getId());

        //Verify
        thrown.expect(NotFoundException.class);
        sut.getDistrict(savedDistrict.getId());
        assertThat(candidateService.exists("55500055501"), is(false));
        assertThat(candidateService.exists("55500055502"), is(false));

    }


    private static CandidateData createCandidateData(String firstName, String lastName, String personId, String partyName) {
        CandidateData cd = new CandidateData();
        cd.setFirstName(firstName);
        cd.setLastName(lastName);
        cd.setPersonId(personId);
        cd.setPartyName(partyName);
        return cd;
    }

    private static CountyData createCountyData(String name, Long voterCount) {
        CountyData cd = new CountyData();
        cd.setName(name);
        cd.setVoterCount(voterCount);
        return cd;
    }

    @TestConfiguration
    static class Config {
        @Bean
        @Primary
        public DataPreloader dataPreloader() {
            return mock(DataPreloader.class);
        }
    }
}
