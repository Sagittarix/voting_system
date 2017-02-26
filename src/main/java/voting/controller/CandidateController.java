package voting.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import voting.dto.CandidateRepresentation;
import voting.service.CandidateService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by domas on 1/10/17.
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/api/candidate")
public class CandidateController {

    private CandidateService candidateService;
    private static final Logger logger = Logger.getLogger(CandidateController.class);

    @Autowired
    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping
    public List<CandidateRepresentation> getCandidates() {
        List<CandidateRepresentation> all =
                candidateService.getCandidates().stream()
                                                .map(CandidateRepresentation::new)
                                                .collect(Collectors.toList());
        logger.debug("All candidates (Candidate) extracted");
        return all;
    }

    @GetMapping("/{id}")
    public CandidateRepresentation getCandidate(@PathVariable Long id) {
        CandidateRepresentation cr = new CandidateRepresentation(candidateService.getCandidate(id));
        logger.debug("Candidate (Candidate) extracted - " + cr);
        return cr;
    }

}
