package voting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import voting.dto.CandidateData;
import voting.dto.CandidateRepresentation;
import voting.service.CandidateService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by domas on 1/10/17.
 */

@RestController
@RequestMapping(path="/api/candidate")
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
        return new CandidateRepresentation(candidateService.getCandidate(id));
    }

    @PostMapping
    public CandidateRepresentation addNewCandidate(@Valid @RequestBody CandidateData candidateData) {
        return new CandidateRepresentation(candidateService.addNewCandidate(candidateData));
    }

    @DeleteMapping("/{id}")
    public void deleteCandidate(@PathVariable Long id) {
        candidateService.deleteCandidate(id);
    }


}
