package voting.results;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import voting.dto.CountyRepresentation;
import voting.factory.RepresentationFactory;
import voting.model.County;
import voting.repository.CountyRepository;

import javax.persistence.EntityManager;
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
    private EntityManager em;

    @Autowired
    public CountyResultService(CountyResultRepository countyResultRepository,
                               CountyRepository countyRepository,
                               CandidateVotesService candidateVotesService,
                               EntityManager em) {
        this.countyResultRepository = countyResultRepository;
        this.countyRepository = countyRepository;
        this.candidateVotesService = candidateVotesService;
        this.em = em;
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

    @Transactional
    public CountyRepresentation confirmResultForCounty(Long countyId, boolean isSingleMandate) {
        County county = countyRepository.findOne(countyId);
        CountyResult cResult = getResult(county, isSingleMandate);
        cResult.setConfirmed(true);
        countyResultRepository.update(cResult);
        return new CountyRepresentation(county);
    }

    public CountyRepresentation deleteResultForCounty(Long countyId, boolean isSingleMandate) {
        County county = countyRepository.findOne(countyId);
        CountyResult cr = getResult(county, isSingleMandate);
        countyResultRepository.delete(cr);
        county.removeResult(cr);
        return new CountyRepresentation(county);
    }

    private CountyResult getResult(County county, boolean isSingleMandate) {
        return county.getCountyResultList()
                     .stream()
                     .filter(cr -> cr.isSingleMandateSystem() == isSingleMandate)
                     .findFirst()
                     .get();
    }
}
