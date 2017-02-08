package voting.service;

import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.*;
import org.springframework.web.multipart.MultipartFile;
import voting.dto.CandidateData;
import voting.dto.PartyData;
import voting.exception.CsvMultiErrorsException;
import voting.exception.DTOMultiFieldsErrorsException;
import voting.exception.DTOMultiObjectsErrorsException;
import voting.exception.NotFoundException;
import voting.model.Candidate;
import voting.model.Party;
import voting.repository.PartyRepository;
import voting.validator.CsvFileValidator;
import voting.validator.PartyValidator;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
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
    private final PartyValidator partyValidator;
    private final CsvFileValidator csvFileValidator;

    @Autowired
    public PartyServiceImpl(PartyRepository partyRepository,
                            CandidateService candidateService,
                            StorageService storageService,
                            ParsingService parsingService,
                            PartyValidator partyValidator,
                            CsvFileValidator csvFileValidator) {
        this.partyRepository = partyRepository;
        this.candidateService = candidateService;
        this.storageService = storageService;
        this.parsingService = parsingService;
        this.partyValidator = partyValidator;
        this.csvFileValidator = csvFileValidator;
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
    public Party saveParty(@Valid PartyData partyData, MultipartFile file) throws IOException, CsvException {

        Errors bindingResult = new BeanPropertyBindingResult(partyData, "partyData");
        ValidationUtils.invokeValidator(partyValidator, partyData, bindingResult);
        List<FieldError> errors = bindingResult.getFieldErrors();

        if (errors.size() > 0) throw new DTOMultiFieldsErrorsException("PartyData binding unsuccessful", errors);

        Party party = null;
        if (partyData.getId() != null) {
            try {
                party = getParty(partyData.getId());
            } catch(NotFoundException nf) {
                bindingResult.rejectValue("id", HttpStatus.NOT_FOUND.toString(), "Spring - Partija pagal ID nerasta");
                throw new DTOMultiFieldsErrorsException("Party not found by ID", bindingResult.getFieldErrors());
            }
            party.setName(partyData.getName());
        } else if (partyRepository.existsByName(partyData.getName())) {
            bindingResult.rejectValue("name", HttpStatus.CONFLICT.toString(), "Spring - Partija su tokiu pavadinimu jau yra");
            if (!errors.isEmpty()) throw new DTOMultiFieldsErrorsException("Party already exists", bindingResult.getFieldErrors());
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

        Errors csvErrors = new BeanPropertyBindingResult(file, "file");
        ValidationUtils.invokeValidator(csvFileValidator, file, csvErrors);

        List<ObjectError> fileErrors = csvErrors.getAllErrors();
        List<ObjectError> candidatesErrors = new ArrayList<>();

        if (!fileErrors.isEmpty()) throw new CsvMultiErrorsException("CSV errors detected", fileErrors);

        List<CandidateData> candidateListData = extractCandidateList(file);
        party.removeAllCandidates();

        candidateListData.forEach(
                candidateData -> {
                    Candidate newCandidate;
                    candidateData.setPartyId(party.getId());
                    candidateData.setPartyName(party.getName());
                    if (candidateService.exists(candidateData.getPersonId())) {
                        Candidate existingCandidate = candidateService.getCandidate(candidateData.getPersonId());
                        try {
                            candidateService.checkCandidateIntegrity(candidateData, existingCandidate);
                        } catch (IllegalArgumentException ia) {
                            candidatesErrors.add(new ObjectError("candidateData", ia.getMessage()));
                        }
                        newCandidate = existingCandidate;
                    } else {
                        newCandidate = candidateService.addNewCandidate(candidateData);
                    }
                    party.addCandidate(newCandidate);
                });
        if (candidatesErrors.size() > 0) throw new DTOMultiObjectsErrorsException("CSV errors detected", candidatesErrors);
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
