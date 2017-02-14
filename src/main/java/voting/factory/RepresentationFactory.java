package voting.factory;

import org.springframework.stereotype.Component;
import voting.dto.CandidateRepresentation;
import voting.dto.CountyRepresentation;
import voting.results.CandidateVotes;
import voting.results.CandidateVotesRepresentation;
import voting.results.CountyResult;
import voting.results.CountyResultRepresentation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrius on 2/9/17.
 */

@Component
public class RepresentationFactory {

    public static CountyResultRepresentation makeRepresentationOf(CountyResult cr) {
        CountyResultRepresentation crr = new CountyResultRepresentation();
        crr.setId(cr.getId());
        crr.setSpoiledBallots(cr.getSpoiledBallots());
        crr.setSingleMandateSystem(cr.isSingleMandateSystem());
        crr.setCreatedOn(cr.getCreatedOn());
        crr.setCountyId(cr.getCounty().getId());
        crr.setCandidateVotesList(makeRepresentationOf(cr.getCandidateVotesList()));

        return crr;
    }

    public static List<CountyResultRepresentation> makeRepresentationOfCountyResultsList(List<CountyResult> list) {
        List<CountyResultRepresentation> crrl = new ArrayList<>();
        list.forEach(cr -> crrl.add(makeRepresentationOf(cr)));
        return crrl;
    }

    public static List<CandidateVotesRepresentation> makeRepresentationOf(List<CandidateVotes> rawList) {
        List<CandidateVotesRepresentation> cvrl = new ArrayList<>();
        rawList.forEach(cv -> {
            cvrl.add(makeRepresentationOf(cv));
        });

        return cvrl;
    }

    public static CandidateVotesRepresentation makeRepresentationOf(CandidateVotes cv) {
        CandidateVotesRepresentation cvr = new CandidateVotesRepresentation();
        cvr.setId(cv.getId());
        cvr.setVotes(cv.getVotes());
        cvr.setCandidate(new CandidateRepresentation(cv.getCandidate()));

        return cvr;
    }
}

