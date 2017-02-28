package voting.controller;

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

    @Autowired
    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping
    public List<CandidateRepresentation> getCandidates() {
        return candidateService.getCandidates().stream()
                                               .map(CandidateRepresentation::new)
                                               .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public CandidateRepresentation getCandidate(@PathVariable Long id) {
        return new CandidateRepresentation(candidateService.getCandidate(id));
    }

}
