package voting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import voting.model.Candidate;
import voting.dto.CandidateData;
import voting.service.CandidateService;

import javax.validation.Valid;
import java.util.List;

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
    public List<Candidate> getCandidates() {
        return candidateService.getCandidates();
    }

    @GetMapping("/{id}")
    public Candidate getCandidate(@PathVariable Long id) {
        return candidateService.getCandidate(id);
    }

    @PostMapping
    public Candidate addNewCandidate(@Valid @RequestBody CandidateData candidateData) {
        return candidateService.addNewCandidate(candidateData);
    }

    @DeleteMapping("/{id}")
    public void deleteCandidate(@PathVariable Long id) {
        candidateService.deleteCandidate(id);
    }


}
