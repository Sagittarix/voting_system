package voting;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import voting.model.*;
import voting.repository.*;
import voting.results.model.result.CountySMResult;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

/**
 * Created by domas on 1/11/17.
 */

@Component
public class DataPreloader implements CommandLineRunner {

    private static Random rand = new Random(0);
    private static Faker faker = new Faker();
    private final CountyRepRepository countyRepRepository;
    private final AdminRepository adminRepRepository;
    private final DistrictRepository districtRepository;
    private final PartyRepository partyRepository;
    private final CandidateRepository candidateRepository;
    private final CountyRepository countyRepository;

    @Autowired
    public DataPreloader(CountyRepRepository countyRepRepository,
                         DistrictRepository districtRepository,
                         PartyRepository partyRepository,
                         CandidateRepository candidateRepository,
                         AdminRepository adminRepository, CountyRepository countyRepository) {
        this.countyRepRepository = countyRepRepository;
        this.districtRepository = districtRepository;
        this.partyRepository = partyRepository;
        this.candidateRepository = candidateRepository;
        this.adminRepRepository = adminRepository;
        this.countyRepository = countyRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        loadStressData();
        System.out.println("=======================");
        System.out.println("STRESS LOADED");
        System.out.println("=======================");

        District district1 = new District("Vilnius");
        District district2 = new District("Kaunas");
        District district3 = new District("Klaipėda");

        County county1 = new County("Senamiestis", 3000L, faker.address().streetAddress());
        County county2 = new County("Naujamiestis", 3000L, faker.address().streetAddress());
        County county3 = new County("Pašilaičiai", 3000L, faker.address().streetAddress());
        County county4 = new County("Senamiestis", 3000L, faker.address().streetAddress());
        County county5 = new County("Šilainiai", 3000L, faker.address().streetAddress());
        County county6 = new County("Sausainiai", 10000L, faker.address().streetAddress());

        List<County> countyList1 = new ArrayList<County>(Arrays.asList(county1, county2, county3));
        List<County> countyList2 = new ArrayList<County>(Arrays.asList(county4, county5));
        List<County> countyList3 = new ArrayList<County>(Arrays.asList(county6));

        countyList1.forEach(district1::addCounty);
        countyList2.forEach(district2::addCounty);
        countyList3.forEach(district3::addCounty);

        districtRepository.save(district1);
        districtRepository.save(district2);
        districtRepository.save(district3);

        /*Candidate cand1 = new Candidate(faker.name().firstName(), faker.name().lastName(), generateRandomPersonId(), faker.shakespeare().hamletQuote());
        Candidate cand2 = new Candidate(faker.name().firstName(), faker.name().lastName(), generateRandomPersonId(), faker.shakespeare().hamletQuote());
        Candidate cand3 = new Candidate(faker.name().firstName(), faker.name().lastName(), generateRandomPersonId(), faker.shakespeare().hamletQuote());
        Candidate cand4 = new Candidate(faker.name().firstName(), faker.name().lastName(), generateRandomPersonId(), faker.shakespeare().hamletQuote());
        Candidate cand5 = new Candidate(faker.name().firstName(), faker.name().lastName(), generateRandomPersonId(), faker.shakespeare().hamletQuote());
        Candidate cand6 = new Candidate(faker.name().firstName(), faker.name().lastName(), generateRandomPersonId(), faker.shakespeare().hamletQuote());
        Candidate cand7 = new Candidate(faker.name().firstName(), faker.name().lastName(), generateRandomPersonId(), faker.shakespeare().hamletQuote());
        Candidate cand8 = new Candidate(faker.name().firstName(), faker.name().lastName(), generateRandomPersonId(), faker.shakespeare().hamletQuote());
        Candidate cand9 = new Candidate(faker.name().firstName(), faker.name().lastName(), generateRandomPersonId(), faker.shakespeare().hamletQuote());
        Candidate cand10 = new Candidate(faker.name().firstName(), faker.name().lastName(), generateRandomPersonId(), faker.shakespeare().hamletQuote());
        Candidate cand11 = new Candidate(faker.name().firstName(), faker.name().lastName(), generateRandomPersonId(), faker.shakespeare().hamletQuote());
        Candidate cand12 = new Candidate(faker.name().firstName(), faker.name().lastName(), generateRandomPersonId(), faker.shakespeare().hamletQuote());
        Candidate cand13 = new Candidate(faker.name().firstName(), faker.name().lastName(), generateRandomPersonId(), faker.shakespeare().hamletQuote());
        Candidate cand14 = new Candidate(faker.name().firstName(), faker.name().lastName(), generateRandomPersonId(), faker.shakespeare().hamletQuote());
        Candidate cand15 = new Candidate(faker.name().firstName(), faker.name().lastName(), generateRandomPersonId(), faker.shakespeare().hamletQuote());
        Candidate cand16 = new Candidate(faker.name().firstName(), faker.name().lastName(), generateRandomPersonId(), faker.shakespeare().hamletQuote());
        Candidate cand17 = new Candidate(faker.name().firstName(), faker.name().lastName(), generateRandomPersonId(), faker.shakespeare().hamletQuote());
        Candidate cand18 = new Candidate(faker.name().firstName(), faker.name().lastName(), generateRandomPersonId(), faker.shakespeare().hamletQuote());

        List<Candidate> candidateList1 = new ArrayList<>(Arrays.asList(cand1, cand6, cand16));
        List<Candidate> candidateList2 = new ArrayList<>(Arrays.asList(cand2, cand7, cand17));
        List<Candidate> candidateList3 = new ArrayList<>(Arrays.asList(cand3, cand8, cand18));*/

        /*party1.addCandidate(cand1);
        party1.addCandidate(cand2);
        party1.addCandidate(cand3);
        party1.addCandidate(cand4);
        party1.addCandidate(cand5);
        party2.addCandidate(cand6);
        party2.addCandidate(cand7);
        party2.addCandidate(cand8);
        party2.addCandidate(cand9);
        party2.addCandidate(cand10);
        party3.addCandidate(cand11);
        party3.addCandidate(cand12);
        party3.addCandidate(cand13);
        party3.addCandidate(cand14);
        party3.addCandidate(cand15);*/

        /*candidateList1.forEach(district1::addCandidate);
        candidateList2.forEach(district2::addCandidate);
        candidateList3.forEach(district3::addCandidate);*/

        /*candidateRepository.save(Arrays.asList(cand1, cand2, cand3, cand4, cand5, cand6, cand7, cand8, cand9, cand10,
                cand11, cand12, cand13, cand14, cand15, cand16, cand17, cand18));*/

        String _1name1 = faker.name().firstName();
        String _2name1 = faker.name().firstName();
        CountyRep cr1 = new CountyRep(
                _1name1,
                _2name1,
                faker.internet().emailAddress(_1name1.toLowerCase().concat(".").concat(_2name1.toLowerCase())),
                "passpass",
                county1,
                new String[]{"ROLE_REPRESENTATIVE"}
        );
        String _1name2 = faker.name().firstName();
        String _2name2 = faker.name().firstName();
        CountyRep cr2 = new CountyRep(
                _1name2,
                _2name2,
                faker.internet().emailAddress(_1name2.toLowerCase().concat(".").concat(_2name2.toLowerCase())),
                "passpass",
                county1,
                new String[]{"ROLE_REPRESENTATIVE"}
        );
        String _1name3 = faker.name().firstName();
        String _2name3 = faker.name().firstName();
        CountyRep cr3 = new CountyRep(
                _1name3,
                _2name3,
                faker.internet().emailAddress(_1name3.toLowerCase().concat(".").concat(_2name3.toLowerCase())),
                "passpass",
                county1,
                new String[]{"ROLE_REPRESENTATIVE"}
        );
        String _1name4 = faker.name().firstName();
        String _2name4 = faker.name().firstName();
        CountyRep cr4 = new CountyRep(
                _1name4,
                _2name4,
                faker.internet().emailAddress(_1name4.toLowerCase().concat(".").concat(_2name4.toLowerCase())),
                "passpass",
                county1,
                new String[]{"ROLE_REPRESENTATIVE"}
        );
        String _1name5 = faker.name().firstName();
        String _2name5 = faker.name().firstName();
        CountyRep cr5 = new CountyRep(
                _1name5,
                _2name5,
                faker.internet().emailAddress(_1name5.toLowerCase().concat(".").concat(_2name5.toLowerCase())),
                "passpass",
                county1,
                new String[]{"ROLE_REPRESENTATIVE"}
        );
        CountyRep cr6 = new CountyRep(
                "Faker",
                "Faaker",
                faker.internet().emailAddress("faker.faker"),
                "faker",
                county1,
                new String[]{"ROLE_REPRESENTATIVE"}
        );

        List<CountyRep> crs = new ArrayList<>(Arrays.asList(new CountyRep[]{cr1, cr2, cr3, cr4, cr5, cr6}));
        countyRepRepository.save(crs);
        Admin admin = new Admin("Admin", "Admin", "admin@admin.lt", new String[]{"ROLE_ADMIN"});
        adminRepRepository.save(admin);
    }

    public static String generateRandomPersonId() {
        StringBuilder id = new StringBuilder();
        id.append(rand.nextInt(2) + 3);
        int year = rand.nextInt(100);
        id.append((year >= 10) ? year : "0"+year);
        int month = rand.nextInt(12) + 1;
        id.append((month >= 10) ? month : "0"+month);
        int bound = (month == 2) ? 28 : 31;
        int day = rand.nextInt(bound) + 1;
        id.append((day >= 10) ? day : "0"+day);
        rand.ints(4, 0, 10).forEach(digit -> id.append(digit));
        return id.toString();
    }

    private void loadStressData() {
        //GENERATES DISTRICTS, COUNTIES AND THEIR VOTER_COUNT
        String districtsFile = "src/main/resources/districts_and_counties.csv";
        //String representativesFile = "src/main/resources/county_representatives.csv";

        BufferedReader fileReader = null;
        //BufferedReader fileReader2 = null;

        final String DELIMITER = ";";
        List<CountyRep> allCountyRepresentatives = new ArrayList<CountyRep>();

        try {
            String line = "";
            fileReader = new BufferedReader(new FileReader(districtsFile));
            //fileReader2 = new BufferedReader(new FileReader(representativesFile));
            long linesCount = fileReader.lines().count();
            fileReader.close();
            fileReader = new BufferedReader(new FileReader(districtsFile));
            String currentDistrictName = "";
            String currentCountyName = "";
            String currentCountyAddress = "";
            String districtName = "";
            String countyName = "";
            String countyAddress = "";

            /*String representativeName = "";
            String representativeSurname = "";
            String representativeEmail = "";
            String representativePassword = "";*/

            long currentVotersCount = 0;
            long votersCount = 0;
            District currentDistrictObject = null;
            int lineNumber = 0;
            //int countyCount = 0;
            while ((line = fileReader.readLine()) != null) {
                lineNumber++;
                if (lineNumber == 1) { continue; }
                int tokenNumber = 0;
                String[] tokens = line.split(DELIMITER);

                for (String token : tokens) {
                    switch (tokenNumber) {
                        case 1:
                            districtName = token;
                            break;
                        case 3:
                            countyName = token;
                            break;
                        case 4:
                            countyAddress = token;
                            break;
                        case 5:
                            votersCount = Integer.parseInt(token);
                            break;
                    }
                    tokenNumber++;
                }
                if (currentDistrictName.equals("") && currentCountyName.equals("") && currentCountyAddress.equals("")) {
                    currentDistrictName = districtName;
                    currentCountyName = countyName;
                    currentCountyAddress = countyAddress;
                    currentDistrictObject = new District(districtName);
                }
                if (!countyName.equals(currentCountyName)) {
                    currentVotersCount = currentVotersCount + votersCount;
                    currentCountyAddress = currentCountyAddress.substring(9);
                    County currentCountyObject = new County(currentCountyName, currentVotersCount, currentCountyAddress);
                    currentDistrictObject.addCounty(currentCountyObject);

                    // GENERATES COUNTY REPRESENTATIVE FOR CURRENT COUNTY OBJECT
                    /*if ((line = fileReader2.readLine()) != null) {
                        int tokenNumber2 = 0;
                        String[] tokens2 = line.split(DELIMITER);
                        for (String token : tokens2) {
                            switch (tokenNumber2) {
                                case 0:
                                    representativeName = token;
                                    break;
                                case 1:
                                    representativeSurname = token;
                                    break;
                                case 2:
                                    representativeEmail = token;
                                    break;
                                case 3:
                                    representativePassword = token;
                                    break;
                            }
                            tokenNumber2++;
                        }
                    }*/

                    String fName = faker.name().firstName();
                    String lName = faker.name().lastName();
                    String email = faker.internet().emailAddress(fName + "." + lName);
                    String password = "password";

                    CountyRep currentCountyRepresentative = new CountyRep(
                            fName,
                            lName,
                            email,
                            password,
                            currentCountyObject,
                            "ROLE_REPRESENTATIVE"
                    );

                    allCountyRepresentatives.add(currentCountyRepresentative);
                    currentVotersCount = 0;
                    //countyCount++;
                } else {
                    currentVotersCount = currentVotersCount + votersCount;
                }

                if (!districtName.equals(currentDistrictName) || linesCount == lineNumber + 1) {
                    districtRepository.save(currentDistrictObject);
                    currentDistrictObject = new District(districtName);
                    //int countFromRepository = StreamSupport.stream(districtRepository.findAll().spliterator(), false).mapToInt(d -> d.getCounties().size()).sum();
                }

                if (!currentDistrictName.equals(districtName)) {
                    currentDistrictName = districtName;
                }

                if (!currentCountyName.equals(countyName)) {
                    currentCountyName = countyName;
                }

                if (!currentCountyAddress.equals(countyAddress)) {
                    currentCountyAddress = countyAddress;
                }
            }
            countyRepRepository.save(allCountyRepresentatives);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            /*try {
                fileReader2.close();
            } catch (IOException e) {
                e.printStackTrace();
            }*/
        }
        System.out.println("=======================");
        System.out.println("territorials created");

        List<Party> parties = createParties();
        System.out.println("=====================");
        System.out.println("parties created");

        List<Candidate> candidates = new ArrayList<>();

        parties.forEach(p -> createCandidates(p, candidates));
        System.out.println("=====================");
        System.out.println("candidates created");

        List<District> districts = new ArrayList<>();
        districtRepository.findAll().forEach(districts::add);
        Random randomizer = new Random();
        int delegates = randomizer.nextInt(parties.size()) + 1;
        districts.forEach(d -> {
           List<Party> randParties = getRandomParties(parties, delegates);
           randParties.forEach(p -> {
               List<Candidate> partyCandidates = p.getCandidates();
               Candidate candidate = new Candidate(
                       faker.name().firstName(),
                       faker.name().lastName(),
                       generateRandomPersonId(),
                       faker.shakespeare().kingRichardIIIQuote(),
                       (long) partyCandidates.size() + 1
               );
               partyCandidates.add(candidate);
               d.addCandidate(candidate);
               candidateRepository.save(candidate);
           });
           IntStream.range(0, delegates).forEach(i -> {
               Candidate candidate = new Candidate(
                       faker.name().firstName(),
                       faker.name().lastName(),
                       generateRandomPersonId(),
                       faker.shakespeare().kingRichardIIIQuote()
               );
               d.addCandidate(candidate);
               candidateRepository.save(candidate);
           });
        });
        districtRepository.save(districts);
        partyRepository.save(parties);
        System.out.println("=====================");
        System.out.println("SM candidates assigned");

        List<County> counties = new ArrayList<>();
        countyRepository.findAll().forEach(counties::add);
        counties.forEach(c -> {
            c.setSmResult(new CountySMResult());
        });

        countyRepository.save(counties);
    }

    private List<Party> getRandomParties(List<Party> list, int num) {
        Set<Party> set = new HashSet<>();
        Random randomizer = new Random();
        IntStream.range(0, num).forEach(i -> set.add((Party)list.get(randomizer.nextInt(num))));
        return new ArrayList<>(set);
    }

    private List<Party> createParties() {
        Party party1 = new Party("Socialdemokratų partija");
        Party party2 = new Party("Tvarka ir tesingumas");
        Party party3 = new Party("Liberalai");
        Party party4 = new Party("Kovotojų už Lietuvą sąjunga");
        Party party5 = new Party("Lietuvos žalioji partija");
        Party party6 = new Party("Lietuvos reformų partija");
        Party party7 = new Party("Žemaičių partija");
        Party party8 = new Party("Lietuvos žmonių partija");
        Party party9 = new Party("Lietuvos sąrašas");
        Party party10 = new Party("Frontas");

        List<Party> parties = new ArrayList<>(
                Arrays.asList(new Party[]{party1, party2, party3, party4, party5, party6, party7, party8, party9, party10})
        );
        partyRepository.save(parties);
        return parties;
    }

    private void createCandidates(Party party, List<Candidate> candidates) {
        IntStream.range(0, 70).forEach(i -> {
            Candidate candidate = new Candidate(
                    faker.name().firstName(),
                    faker.name().lastName(),
                    generateRandomPersonId(),
                    faker.shakespeare().kingRichardIIIQuote(),
                    (long) i+1
            );
            party.addCandidate(candidate);
            candidates.add(candidate);
        });
        partyRepository.save(party);
        candidateRepository.save(candidates);
    }
}
