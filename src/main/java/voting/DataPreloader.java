package voting;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import voting.dto.district.DistrictData;
import voting.model.*;
import voting.repository.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by domas on 1/11/17.
 */

@Component
public class DataPreloader implements CommandLineRunner {

    private static Random rand = new Random(0);
    private final CountyRepRepository countyRepRepository;
    private final AdminRepository adminRepRepository;
    private final DistrictRepository districtRepository;
    private final PartyRepository partyRepository;
    private final CandidateRepository candidateRepository;

    @Autowired
    public DataPreloader(CountyRepRepository countyRepRepository,
                         DistrictRepository districtRepository,
                         PartyRepository partyRepository,
                         CandidateRepository candidateRepository,
                         AdminRepository adminRepository) {
        this.countyRepRepository = countyRepRepository;
        this.districtRepository = districtRepository;
        this.partyRepository = partyRepository;
        this.candidateRepository = candidateRepository;
        this.adminRepRepository = adminRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        // loadStressData();

        DistrictData d1 = new DistrictData();
        d1.setName("Vilnius");


        District district1 = new District("Vilnius");
        District district2 = new District("Kaunas");
        District district3 = new District("Klaipėda");

        County county1 = new County("Senamiestis", 3000L, "Adresas 1");
        County county2 = new County("Naujamiestis", 3000L, "Adresas 2");
        County county3 = new County("Pašilaičiai", 3000L, "Adresas 3");
        County county4 = new County("Senamiestis", 3000L, "Adresas 4");
        County county5 = new County("Šilainiai", 3000L, "Adresas 5");
        County county6 = new County("Unknown", 10000L, "Unknown address");

        List<County> countyList1 = new ArrayList<County>(Arrays.asList(county1, county2, county3));
        List<County> countyList2 = new ArrayList<County>(Arrays.asList(county4, county5));
        List<County> countyList3 = new ArrayList<County>(Arrays.asList(county6));

        countyList1.forEach(c -> district1.addCounty(c));
        countyList2.forEach(c -> district2.addCounty(c));
        countyList3.forEach(c -> district3.addCounty(c));

        districtRepository.save(district1);
        districtRepository.save(district2);
        districtRepository.save(district3);

        Party party1 = new Party("Demokratai");
        Party party2 = new Party("Tvarka ir tesingumas");
        Party party3 = new Party("Liberalai");

        partyRepository.save(Arrays.asList(party1, party2, party3));

        Candidate cand1 = new Candidate("Pirmas", "Pirmas", "55500055501", "Ilgas kandidato aprasymas kad virsytu 20 simboliu");
        Candidate cand2 = new Candidate("Antras", "Antras", "55500055502", "Ilgas kandidato aprasymas kad virsytu 20 simboliu");
        Candidate cand3 = new Candidate("Trecias", "Trecias", "55500055503", "Ilgas kandidato aprasymas kad virsytu 20 simboliu");
        Candidate cand4 = new Candidate("Ketvirtas", "Ketvirtas", "55500055504", "Ilgas kandidato aprasymas kad virsytu 20 simboliu");
        Candidate cand5 = new Candidate("Penktas", "Penktas", "55500055505", "Ilgas kandidato aprasymas kad virsytu 20 simboliu");
        Candidate cand6 = new Candidate("Sestas", "Sestas", "55500055506", "Ilgas kandidato aprasymas kad virsytu 20 simboliu");
        Candidate cand7 = new Candidate("Septintas", "Septintas", "55500055507", "Ilgas kandidato aprasymas kad virsytu 20 simboliu");
        Candidate cand8 = new Candidate("Astuntas", "Astuntas", "55500055508", "Ilgas kandidato aprasymas kad virsytu 20 simboliu");
        Candidate cand9 = new Candidate("Devintas", "Devintas", "55500055509", "Ilgas kandidato aprasymas kad virsytu 20 simboliu");
        Candidate cand10 = new Candidate("Desimtas", "Desimtas", "55500055510", "Ilgas kandidato aprasymas kad virsytu 20 simboliu");
        Candidate cand11 = new Candidate("Vienuoliktas", "Vienuoliktas", "55500055511", "Ilgas kandidato aprasymas kad virsytu 20 simboliu");
        Candidate cand12 = new Candidate("Dvyliktas", "Dvyliktas", "55500055512", "Ilgas kandidato aprasymas kad virsytu 20 simboliu");
        Candidate cand13 = new Candidate("Tryliktas", "Tryliktas", "55500055513", "Ilgas kandidato aprasymas kad virsytu 20 simboliu");
        Candidate cand14 = new Candidate("Keturioliktas", "Keturioliktas", "55500055514", "Ilgas kandidato aprasymas kad virsytu 20 simboliu");
        Candidate cand15 = new Candidate("Penkioliktas", "Penkioliktas", "55500055515", "Ilgas kandidato aprasymas kad virsytu 20 simboliu");
        Candidate cand16 = new Candidate("Sesioliktas", "Sesioliktas", "55500055516", "Ilgas kandidato aprasymas kad virsytu 20 simboliu");
        Candidate cand17 = new Candidate("Septynioliktas", "Septynioliktas", "55500055517", "Ilgas kandidato aprasymas kad virsytu 20 simboliu");
        Candidate cand18 = new Candidate("Astuonioliktas", "Astuonioliktas", "55500055518", "Ilgas kandidato aprasymas kad virsytu 20 simboliu");

        List<Candidate> candidateList1 = new ArrayList<>(Arrays.asList(cand1, cand2, cand16));
        List<Candidate> candidateList2 = new ArrayList<>(Arrays.asList(cand6, cand7, cand17));
        List<Candidate> candidateList3 = new ArrayList<>(Arrays.asList(cand11, cand12, cand18));

        party1.addCandidate(cand1);
        party1.addCandidate(cand4);
        party1.addCandidate(cand7);
        party1.addCandidate(cand10);
        party1.addCandidate(cand13);
        party2.addCandidate(cand2);
        party2.addCandidate(cand5);
        party2.addCandidate(cand8);
        party2.addCandidate(cand11);
        party2.addCandidate(cand14);
        party3.addCandidate(cand3);
        party3.addCandidate(cand6);
        party3.addCandidate(cand9);
        party3.addCandidate(cand12);
        party3.addCandidate(cand15);

        candidateList1.forEach(district1::addCandidate);
        candidateList2.forEach(district2::addCandidate);
        candidateList3.forEach(district3::addCandidate);


        candidateRepository.save(Arrays.asList(cand1, cand2, cand3, cand4, cand5, cand6, cand7, cand8, cand9, cand10,
                cand11, cand12, cand13, cand14, cand15, cand16, cand17, cand18));

        Faker faker = new Faker();
        CountyRep cr1 = new CountyRep(
                faker.name().firstName(),
                faker.name().lastName(),
                "test1@test.lt",
                "password1",
                county1,
                new String[]{"REPRESENTATIVE"}
        );
        CountyRep cr2 = new CountyRep(
                faker.name().firstName(),
                faker.name().lastName(),
                "test1@test.lt",
                "password2",
                county2,
                new String[]{"REPRESENTATIVE"}
        );
        CountyRep cr3 = new CountyRep(
                faker.name().firstName(),
                faker.name().lastName(),
                "test1@test.lt",
                "password3",
                county3,
                new String[]{"REPRESENTATIVE"}
        );
        CountyRep cr4 = new CountyRep(
                faker.name().firstName(),
                faker.name().lastName(),
                "test1@test.lt",
                "password4",
                county4,
                new String[]{"REPRESENTATIVE"}
        );
        CountyRep cr5 = new CountyRep(
                faker.name().firstName(),
                faker.name().lastName(),
                "test1@test.lt",
                "password5",
                county5,
                new String[]{"REPRESENTATIVE"}
        );
        CountyRep cr6 = new CountyRep(
                "As",
                "As",
                "rep@rep.lt",
                "asas",
                county6,
                new String[]{"ROLE_REPRESENTATIVE"}
        );

        List<CountyRep> crs = new ArrayList<CountyRep>() {{
            add(cr1);
            add(cr2);
            add(cr3);
            add(cr4);
            add(cr5);
            add(cr6);
        }};
        countyRepRepository.save(crs);

        Admin admin = new Admin(
                "Admin",
                "Admin",
                "admin@admin.lt",
                new String[]{"ROLE_ADMIN"}
        );

        adminRepRepository.save(admin);
    }

    public static String generateRandomPersonId() {
        StringBuilder id = new StringBuilder();
        rand.ints(11, 0, 10).forEach(digit -> id.append(digit));
        System.out.println(id.toString());
        return id.toString();
    }

    private void loadStressData() {
        // GENERATES DISTRICTS, COUNTIES AND THEIR VOTER_COUNT
        String districtsFile = "src/main/resources/districts_and_counties.csv";
        String representativesFile = "src/main/resources/county_representatives.csv";
        BufferedReader fileReader = null;
        BufferedReader fileReader2 = null;
        final String DELIMITER = ";";
        List<CountyRep> allCountyRepresentatives = new ArrayList<CountyRep>();
        try {
            String line = "";
            fileReader = new BufferedReader(new FileReader(districtsFile));
            fileReader2 = new BufferedReader(new FileReader(representativesFile));
            long linesCount = fileReader.lines().count();
            fileReader = new BufferedReader(new FileReader(districtsFile));
            String currentDistrictName = "";
            String currentCountyName = "";
            String currentCountyAddress = "";
            String districtName = "";
            String countyName = "";
            String countyAddress = "";
            String representativeName = "";
            String representativeSurname = "";
            String representativeEmail = "";
            String representativePassword = "";
            long currentVotersCount = 0;
            long votersCount = 0;
            District currentDistrictObject = null;
            int lineNumber = 0;
//            int countyCount = 0;
            while ((line = fileReader.readLine()) != null) {
                lineNumber++;
                if (lineNumber == 1) {
                    continue;
                }
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
                    if ((line = fileReader2.readLine()) != null) {
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
                    }
                    CountyRep currentCountyRepresentative = new CountyRep(representativeName, representativeSurname, representativeEmail, representativePassword, currentCountyObject);
                    allCountyRepresentatives.add(currentCountyRepresentative);
                    currentVotersCount = 0;
//                    countyCount++;
                } else {
                    currentVotersCount = currentVotersCount + votersCount;
                }
                if (!districtName.equals(currentDistrictName) || linesCount == lineNumber + 1) {
                    districtRepository.save(currentDistrictObject);
                    currentDistrictObject = new District(districtName);
//                    int countFromRepository = StreamSupport.stream(districtRepository.findAll().spliterator(), false).mapToInt(d -> d.getCounties().size()).sum();
//                    System.out.println(countyCount + " " + countFromRepository); // @OneToMany(fetch= FetchType.EAGER, mappedBy = "district", cascade = CascadeType.ALL, orphanRemoval = true)
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
            try {
                fileReader2.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
