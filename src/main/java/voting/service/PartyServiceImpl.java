package voting.service;

import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.MultipartFile;
import voting.dto.CandidateData;
import voting.dto.PartyData;
import voting.exception.NotFoundException;
import voting.model.Candidate;
import voting.model.Party;
import voting.repository.PartyRepository;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * Created by domas on 1/15/17.
 */

@Transactional(rollbackFor = Exception.class)
@Service
public class PartyServiceImpl implements PartyService {

    private final PartyRepository partyRepository;
    private final CandidateService candidateService;
    private final StorageService storageService;
    private final ParsingService parsingService;

    @Autowired
    public PartyServiceImpl(PartyRepository partyRepository,
                            CandidateService candidateService,
                            StorageService storageService,
                            ParsingService parsingService) {
        this.partyRepository = partyRepository;
        this.candidateService = candidateService;
        this.storageService = storageService;
        this.parsingService = parsingService;
    }

    @Override
    public Party getParty(Long id) {
        Party party = partyRepository.findOne(id);
        if (party == null) {
            throw (new NotFoundException("Couldn't find party with id " + id));
        }
        return party;
    }

    @Override
    public Party getParty(String name) {
        Party party = partyRepository.findByName(name);
        if (party == null) {
            throw (new NotFoundException("Couldn't find party with name " + name));
        }
        return party;
    }

    @Override
    public List<Party> getParties() {
        return (List<Party>) partyRepository.findAll();
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public Party saveParty(PartyData partyData, MultipartFile file) throws IOException, CsvException {

        Party party;
        if (partyData.getId() != null) {
            party = getParty(partyData.getId());
            party.setName(partyData.getName());
        } else if (partyRepository.existsByName(partyData.getName())) {
            throw (new IllegalArgumentException("Partija su tokiu pavadinimu jau yra"));
        } else {
            party = new Party(partyData.getName());
            party = partyRepository.save(party);
        }

        saveCandidateList(party, file);
        return party;
    }

    @Override
    public Party setCandidateList(Long id, MultipartFile file) throws IOException, CsvException {
        return saveCandidateList(getParty(id), file);
    }

    @Transactional
    @Override
    public void deleteCandidateList(Long id) {
        Party party = getParty(id);
        party.removeAllCandidates();
        partyRepository.save(party);
    }

    @Transactional
    @Override
    public void deleteParty(Long id) {
        Party party = getParty(id);
        party.removeAllCandidates();
        partyRepository.delete(id);
    }

    @Override
    public boolean exists(Long id) {
        return partyRepository.exists(id);
    }

    @Override
    public boolean exists(String name) {
        return partyRepository.existsByName(name);
    }

    @Transactional(rollbackFor = Exception.class)
    private Party saveCandidateList(Party party, MultipartFile file) throws IOException, CsvException {

        List<CandidateData> candidateListData = extractCandidateList(file);
        party.removeAllCandidates();

        candidateListData.forEach(
                candidateData -> {
                    candidateData.setPartyId(party.getId());
                    candidateData.setPartyName(party.getName());
                    Candidate candidate = candidateService.addNewOrGetIfExists(candidateData);
                    party.addCandidate(candidate);
                });
        partyRepository.save(party);

        String fileName = String.format("party_%d.csv", party.getId());
        storageService.store(fileName, file);

        return party;
    }

    private List<CandidateData> extractCandidateList(MultipartFile file) throws IOException, CsvException {
        Path tempFile = storageService.storeTemporary(file);

        List<CandidateData> candidateListData;
        try {
            candidateListData = parsingService.parseMultiMandateCandidateList(tempFile.toFile());
        } finally {
            storageService.delete(tempFile);
        }
        return candidateListData;
    }

}
