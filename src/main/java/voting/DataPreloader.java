package voting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import voting.model.*;
import voting.repository.CandidateRepository;
import voting.repository.CountyRepRepository;
import voting.repository.DistrictRepository;
import voting.repository.PartyRepository;

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
    private final DistrictRepository districtRepository;
    private final PartyRepository partyRepository;
    private final CandidateRepository candidateRepository;

    @Autowired
    public DataPreloader(CountyRepRepository countyRepRepository,
                         DistrictRepository districtRepository,
                         PartyRepository partyRepository,
                         CandidateRepository candidateRepository) {
        this.countyRepRepository = countyRepRepository;
        this.districtRepository = districtRepository;
        this.partyRepository = partyRepository;
        this.candidateRepository = candidateRepository;
    }


    @Override
    public void run(String... strings) throws Exception {
        
        District district1 = new District("Vilnius");
        District district2 = new District("Kaunas");
        District district3 = new District("Klaipėda");
        District district4 = new District("District 4");
        District district5 = new District("District asd");

        County county1 = new County("Senamiestis", 3000L, "Adresas 1");
        County county2 = new County("Naujamiestis", 3000L, "Adresas 2");
        County county3 = new County("Pašilaičiai", 3000L, "Adresas 3");
        County county4 = new County("Senamiestis", 3000L, "Adresas 4");
        County county5 = new County("Šilainiai", 3000L, "Adresas 5");

        List<County> countyList1 = new ArrayList<County>(Arrays.asList(county1, county2, county3));
        List<County> countyList2 = new ArrayList<County>(Arrays.asList(county4, county5));

        countyList1.forEach(c -> district1.addCounty(c));
        countyList2.forEach(c -> district2.addCounty(c));

        districtRepository.save(district1);
        districtRepository.save(district2);
        districtRepository.save(district3);
        districtRepository.save(district4);
        districtRepository.save(district5);

        Party party1 = new Party("Demokratai");
        Party party2 = new Party("Tvarka ir tesingumas");
        Party party3 = new Party("Liberalai");

        partyRepository.save(Arrays.asList(party1, party2, party3));

        Candidate cand1 = new Candidate("Jonas", "Joinaitis", "55500055501");
        Candidate cand2 = new Candidate("Petras", "Petraitis", "55500055502");
        Candidate cand3 = new Candidate("Jonas", "Kubilius", "55500055503");
        Candidate cand4 = new Candidate("Jonas", "Adamkus", "55500055504");
        Candidate cand5 = new Candidate("Jonas", "Grybas", "55500055505");
        Candidate cand6 = new Candidate("Jonas", "Alekna", "55500055506");
        Candidate cand7 = new Candidate("Jonas", "Juršėnas", "55500055507");
        Candidate cand8 = new Candidate("Jonas", "Uspaskichas", "55500055508");
        Candidate cand9 = new Candidate("Jonas", "Aparatas", "55500055509");

        List<Candidate> candidateList1 = new ArrayList<Candidate>(Arrays.asList(cand1, cand2, cand3, cand4));
        List<Candidate> candidateList2 = new ArrayList<Candidate>(Arrays.asList(cand5, cand6, cand7));

        party1.addCandidate(cand1);
        party1.addCandidate(cand2);
        party2.addCandidate(cand3);
        party2.addCandidate(cand6);
        party3.addCandidate(cand7);
        party3.addCandidate(cand8);
        party3.addCandidate(cand9);

        candidateList1.forEach(c -> {
            district1.addCandidate(c);
            candidateRepository.save(c);
        });

        candidateList2.forEach(c -> {
            district2.addCandidate(c);
            candidateRepository.save(c);
        });

        CountyRep cr1 = new CountyRep("CR1001first", "CR001last", "test1@test.lt", county1);
        CountyRep cr2 = new CountyRep("CR002first", "CR002last", "test1@test.lt", county2);
        CountyRep cr3 = new CountyRep("CR003first", "CR003last", "test1@test.lt", county3);
        CountyRep cr4 = new CountyRep("CR004first", "CR004last", "test1@test.lt", county4);
        CountyRep cr5 = new CountyRep("CR005first", "CR005last", "test1@test.lt", county5);
        List<CountyRep> crs = new ArrayList<CountyRep>() {{
            add(cr1);
            add(cr2);
            add(cr3);
            add(cr4);
            add(cr5);
        }};
        countyRepRepository.save(crs);

    }

    public static String generateRandomPersonId() {
        StringBuilder id = new StringBuilder();
        rand.ints(11, 0, 10).forEach(digit -> id.append(digit));
        System.out.println(id.toString());
        return id.toString();
    }
}
