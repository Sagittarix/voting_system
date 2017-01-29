package voting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import voting.dto.CandidateRepresentation;
import voting.exception.NotFoundException;
import voting.model.Candidate;
import voting.service.CandidateService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by domas on 1/10/17.
 */

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
        return candidateService.getCandidates().stream().map(c -> new CandidateRepresentation(c)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CandidateRepresentation getCandidate(@PathVariable Long id) {
        Candidate candidate = candidateService.getCandidate(id);
        if (candidate == null) {
            throw (new NotFoundException("Couldn't find candidate with id " + id));
        }
        return new CandidateRepresentation(candidate);
    }

}
