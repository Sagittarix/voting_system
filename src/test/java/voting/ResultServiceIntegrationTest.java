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
import voting.dto.CandidateData;
import voting.dto.CountyData;
import voting.dto.DistrictData;
import voting.dto.PartyData;
import voting.model.Candidate;
import voting.model.County;
import voting.model.District;
import voting.model.Party;
import voting.results.ResultService;
import voting.results.model.result.CountyMMResult;
import voting.results.model.result.CountySMResult;
import voting.results.model.result.Result;
import voting.results.model.votecount.CandidateVoteCount;
import voting.results.model.votecount.PartyVoteCount;
import voting.service.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by domas on 2/22/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ResultServiceIntegrationTest {

    private static Path tmpFilePath;
    private static CandidateData candidateData1; // both SM (district1) & MM, belongs to party1
    private static CandidateData candidateData2; // only SM (district1), belongs to party1, but not in a list
    private static CandidateData candidateData3; // only SM (district1), doesn't belong to party
    private static CandidateData candidateData4; // only MM, belongs to party2
    private static CountyData countyData1; // bound to district1
    private static CountyData countyData2; // bound to district1
    private static CountyData countyData3; // bound to district2

    private static DistrictData districtData1;
    private static DistrictData districtData2;

    private static PartyData partyData1;
    private static PartyData partyData2;

    District district1;
    District district2;
    County county1;
    County county2;
    County county3;
    Candidate candidate1;
    Candidate candidate2;
    Candidate candidate3;
    Candidate candidate4;
    Party party1;
    Party party2;
    private List<CountyData> countyDataList;
    private List<CandidateData> candidateDataList;
    private MockMultipartFile multiPartFile = new MockMultipartFile("file.csv", new byte[] {});


    @MockBean
    private ParsingService parsingService;
    @MockBean
    private StorageService storageService;

    @InjectMocks
    @Autowired
    private DistrictService districtService;
    @InjectMocks
    @Autowired
    private PartyService partyService;
    @Autowired
    private CandidateService candidateService;


    @Autowired
    private ResultService resultService;


    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @BeforeClass
    public static void beforeClassSetup() throws IOException {
        tmpFilePath = Files.createTempFile("file", "tmp");
        candidateData1 = createCandidateData("Pirmas", "Pirmas", "55500055501", "Partija 1", 1L);
        candidateData2 = createCandidateData("Antras", "Antras", "55500055502", "Partija 1", null);
        candidateData3 = createCandidateData("Trecias", "Trecias", "55500055503", "Išsikėlęs pats", null);
        candidateData4 = createCandidateData("Ketvirtas", "Ketvirtas", "55500055504", "Partija 2", 1L);
        countyData1 = createCountyData("Apylinkė 1", 3000L);
        countyData2 = createCountyData("Apylinkė 2", 4000L);
        countyData3 = createCountyData("Apylinkė 3", 5000L);

        districtData1 = new DistrictData();
        districtData1.setName("APYGARDA 1");
        districtData1.setCountiesData(Arrays.asList(countyData1, countyData2));

        districtData2 = new DistrictData();
        districtData2.setName("APYGARDA 2");
        districtData2.setCountiesData(Arrays.asList(countyData3));

        partyData1 = new PartyData();
        partyData1.setName("Partija 1");

        partyData2 = new PartyData();
        partyData2.setName("Partija 2");

    }

    @Before
    public void beforeTestSetup() throws IOException, CsvException {
        when(storageService.store(any(), any())).thenReturn(tmpFilePath);
        when(storageService.storeTemporary(any())).thenReturn(tmpFilePath);

        district1 = districtService.addNewDistrict(districtData1);
        district2 = districtService.addNewDistrict(districtData2);

        candidateDataList = Arrays.asList(candidateData1, candidateData2, candidateData3);
        when(parsingService.parseSingleMandateCandidateList(any())).thenReturn(candidateDataList);
        districtService.setCandidateList(district1.getId(), multiPartFile);


        candidateDataList = Arrays.asList(candidateData1);
        when(parsingService.parseMultiMandateCandidateList(any())).thenReturn(candidateDataList);
        party1 = partyService.saveParty(partyData1, multiPartFile);

        candidateDataList = Arrays.asList(candidateData4);
        when(parsingService.parseMultiMandateCandidateList(any())).thenReturn(candidateDataList);
        party2 = partyService.saveParty(partyData2, multiPartFile);


        county1 = districtService.getCounty(1L);
        county2 = districtService.getCounty(2L);
        county3 = districtService.getCounty(3L);

        candidate1 = candidateService.getCandidate(1L);
        candidate2 = candidateService.getCandidate(2L);
        candidate3 = candidateService.getCandidate(3L);
        candidate4 = candidateService.getCandidate(4L);

    }






    @Test
    public void savesSMResultsCorrectly() {

        //Setup
        CandidateVoteCount cvc1 = new CandidateVoteCount(candidate1, 150L);
        CandidateVoteCount cvc2 = new CandidateVoteCount(candidate2, 200L);
        CandidateVoteCount cvc3 = new CandidateVoteCount(candidate3, 250L);

        CountySMResult smResult = new CountySMResult();
        smResult.addVoteCount(cvc1);
        smResult.addVoteCount(cvc2);
        smResult.addVoteCount(cvc3);
        smResult.setCounty(county1);

        //Exercise
        CountySMResult result = resultService.addCountySMResult(smResult);

        //Verify
        List<CandidateVoteCount> votes = result.getVotes();
        assertThat(votes.size(), is(3));
        assertThat(votes.get(0).getCandidate(), is(candidate1));
        assertThat(votes.get(0).getVotes(), is(150L));
        assertThat(votes.get(1).getCandidate(), is(candidate2));
        assertThat(votes.get(1).getVotes(), is(200L));
        assertThat(votes.get(2).getCandidate(), is(candidate3));
        assertThat(votes.get(2).getVotes(), is(250L));

    }


    @Test
    public void savesMMResultsCorrectly() {

        //Setup
        PartyVoteCount pvc1 = new PartyVoteCount(party1, 1000L);
        PartyVoteCount pvc2 = new PartyVoteCount(party2, 2000L);

        CountyMMResult mmResult = new CountyMMResult();
        mmResult.addVoteCount(pvc1);
        mmResult.addVoteCount(pvc2);
        mmResult.setCounty(county1);

        //Exercise
        CountyMMResult result = resultService.addCountyMMResult(mmResult);
        List<PartyVoteCount> votes = result.getVotes();
        //Verify
        assertThat(votes.size(), is(2));
        assertThat(votes.get(0).getParty(), is(party1));
        assertThat(votes.get(0).getVotes(), is(1000L));
        assertThat(votes.get(1).getParty(), is(party2));
        assertThat(votes.get(1).getVotes(), is(2000L));

    }


    @Test
    public void getsByTypeCorrectly() {

        //Setup
        CandidateVoteCount cvc1 = new CandidateVoteCount(candidate1, 150L);
        CandidateVoteCount cvc2 = new CandidateVoteCount(candidate2, 200L);
        CandidateVoteCount cvc3 = new CandidateVoteCount(candidate3, 250L);
        CountySMResult smResult = new CountySMResult();
        smResult.addVoteCount(cvc1);
        smResult.addVoteCount(cvc2);
        smResult.addVoteCount(cvc3);
        smResult.setCounty(county1);

        Result result1= resultService.addCountySMResult(smResult);


        PartyVoteCount pvc1 = new PartyVoteCount(party1, 1000L);
        PartyVoteCount pvc2 = new PartyVoteCount(party2, 2000L);
        CountyMMResult mmResult = new CountyMMResult();
        mmResult.addVoteCount(pvc1);
        mmResult.addVoteCount(pvc2);
        mmResult.setCounty(county1);

        Result result2 = resultService.addCountyMMResult(mmResult);


        //Exercise
        CountySMResult result = resultService.getCountySMResult(county1.getId());

//        Verify
        System.out.println(result);
        assertThat(result.getVotes().size(), is(3));

    }


    private static CandidateData createCandidateData(String firstName, String lastName, String personId, String partyName, Long position) {
        CandidateData cd = new CandidateData();
        cd.setFirstName(firstName);
        cd.setLastName(lastName);
        cd.setPersonId(personId);
        cd.setPartyName(partyName);
        cd.setPositionInPartyList(position);
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
