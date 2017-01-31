package voting.results;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import voting.repository.CandidateRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrius on 1/24/17.
 */

@Service
public class CandidateVotesService {

    @Autowired
    private CandidateRepository candidateRepository;

    public List<CandidateVotes> mapCollectionDataToEntities(List<CandidateVotesDataModel> list, CountyResult cr) {
        List<CandidateVotes> candidateVotesList = new ArrayList<CandidateVotes>();
        list.forEach(cvdm -> {
            CandidateVotes cv = new CandidateVotes();
            cv.setCount(cvdm.getCount());
            cv.setCountyResult(cr);
            cv.setCandidate(candidateRepository.findOne(cvdm.getCandidateId()));
            candidateVotesList.add(cv);
        });
        return candidateVotesList;
    }
}
