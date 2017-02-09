package voting.results;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import voting.factory.RepresentationFactory;
import voting.model.County;
import voting.repository.CountyRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by andrius on 1/24/17.
 */

@Service
public class CountyResultService {

    private CountyResultRepository countyResultRepository;
    private CountyRepository countyRepository;
    private CandidateVotesService candidateVotesService;

    @Autowired
    public CountyResultService(CountyResultRepository countyResultRepository, CountyRepository countyRepository, CandidateVotesService candidateVotesService) {
        this.countyResultRepository = countyResultRepository;
        this.countyRepository = countyRepository;
        this.candidateVotesService = candidateVotesService;
    }

    public List<CountyResult> getAllForSingleMandate() {
        return countyResultRepository.findAll().stream()
                                               .filter(cr -> cr.isSingleMandateSystem() == true)
                                               .collect(Collectors.toList());
    }

    public List<CountyResult> getAllForMultiMandate() {
        return countyResultRepository.findAll().stream()
                                               .filter(cr -> cr.isSingleMandateSystem() == false)
                                               .collect(Collectors.toList());
    }

    @Transactional
    public CountyResultRepresentation save(CountyResultDataModel crdm) {
        CountyResult cr = mapDataWithCollectionToEntity(crdm);
        countyResultRepository.save(cr);
        return RepresentationFactory.makeRepresentationOf(cr);
    }

    public List<CountyResult> getCountyResultsByMandate(Long county_id, boolean isSingleMandate) {
        County county = countyRepository.findOne(county_id);
        return countyResultRepository.findAll().stream()
                                               .filter(cr -> cr.getCounty().equals(county))
                                               .filter(cr -> cr.isSingleMandateSystem() == isSingleMandate)
                                               .collect(Collectors.toList());
    }

    public CountyResult mapDataWithCollectionToEntity(CountyResultDataModel crdm) {
        CountyResult cr = new CountyResult();
        List<CandidateVotes> votesList = candidateVotesService.mapCollectionDataToEntities(crdm.getCandidateVotesDataModelsList(), cr);

        cr.setSingleMandateSystem(crdm.isSingleMandateSystem());
        cr.setSpoiledBallots(crdm.getSpoiledBallots());
        cr.setCandidateVotesList(votesList);
        cr.setCounty(countyRepository.findOne(crdm.getCountyId()));
        return cr;
    }
}
