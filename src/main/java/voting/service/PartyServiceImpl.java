package voting.service;

import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import voting.dto.CandidateData;
import voting.dto.PartyData;
import voting.exception.NotFoundException;
import voting.model.Candidate;
import voting.model.Party;
import voting.repository.PartyRepository;

import java.io.IOException;
import java.util.List;

/**
 * Created by domas on 1/15/17.
 */
@Service
public class PartyServiceImpl implements PartyService {

    private PartyRepository partyRepository;
    private CandidateService candidateService;
    private final StorageService storageService;
    private final ParsingService parsingService;

    @Autowired
    public PartyServiceImpl(PartyRepository partyRepository, CandidateService candidateService, StorageService storageService, ParsingService parsingService) {
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


    //TODO: isspresti sia problema:
    /*dabar kai gaunam nauja partija su kandidatu failu, is pradziu issaugo partija, tik tada seivina sarasa.
    * Not good, nes gaunasi non-transactional. Jeigu partijos data ok, o sarasas blogas, tai issaugos nauja partija
    * su tusciu kandidatu listu. */
    //TODO: isspresta kol kas, bet super ugly, reik normalu sprendima surast

    @Transactional
    @Override
    public Party saveParty(PartyData partyData, MultipartFile file) throws IOException, CsvException {
        boolean newParty;
        Party party;
        if (partyData.getId() != null) {
            newParty = false;
            party = getParty(partyData.getId());
        } else {
            newParty = true;
            party = new Party(partyData.getName());
            party = partyRepository.save(party);
        }

        try {
            party = setCandidateList(party.getId(), file);
            if (newParty) {
                party.setName(partyData.getName());
            }
        } catch (IOException | CsvException ex) {
            if (newParty) {
                partyRepository.delete(party.getId());
            }
            throw (ex);
        }

        return party;
    }

    @Transactional
    @Override
    public Party setCandidateList(Long id, MultipartFile file) throws IOException, CsvException {
        Party party = getParty(id);

        String fileName = String.format("party_%d.csv", party.getId());
        storageService.store(fileName, file);
        Resource fileResource = storageService.loadAsResource(fileName);
        List<CandidateData> candidateListData = parsingService.parseMultiMandateCandidateList(fileResource.getFile());

        party.removeAllCandidates();

        candidateListData.forEach(
                candidateData -> {
                    Candidate newCandidate;
                    try {
                        Candidate oldCandidate = candidateService.getCandidate(candidateData.getPersonId());
                        CandidateService.checkCandidateIntegrity(candidateData, oldCandidate);
                        newCandidate = oldCandidate;
                    } catch (NotFoundException ex) {
                        newCandidate = candidateService.addNewCandidate(candidateData);
                    }
                    party.addCandidate(newCandidate);
                });
        return partyRepository.save(party);
    }

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
}
