package voting.service.results;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import voting.dto.CountyRepresentation;
import voting.dto.results.CountyResultDataModel;
import voting.dto.results.CountyResultRepresentation;
import voting.model.County;
import voting.model.results.CountyResult;
import voting.model.results.UnitVotes;
import voting.repository.results.CountyResultRepository;
import voting.service.DistrictService;
import voting.utils.RepresentationFactory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by andrius on 1/24/17.
 */

@Service
public class CountyResultService {

    private DistrictService districtService;
    private CountyResultRepository countyResultRepository;
    private UnitVotesService unitVotesService;

    @Autowired
    public CountyResultService(CountyResultRepository countyResultRepository, DistrictService districtService, UnitVotesService unitVotesService) {
        this.countyResultRepository = countyResultRepository;
        this.districtService = districtService;
        this.unitVotesService = unitVotesService;
    }


    public List<CountyResult> getAllCountyResults() {
        return countyResultRepository.findAll();
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
        County county = districtService.getCounty(county_id);
        return countyResultRepository.findAll().stream()
                                               .filter(cr -> cr.getCounty().equals(county))
                                               .filter(cr -> cr.isSingleMandateSystem() == isSingleMandate)
                                               .collect(Collectors.toList());
    }

    public CountyResult mapDataWithCollectionToEntity(CountyResultDataModel crdm) {
        CountyResult cr = new CountyResult();;
        List<UnitVotes> votesList = unitVotesService.mapCollectionDataToEntities(
                crdm.getUnitVotesDataModelsList(),
                crdm.isSingleMandateSystem(),
                cr);
        cr.setSingleMandateSystem(crdm.isSingleMandateSystem());
        cr.setSpoiledBallots(crdm.getSpoiledBallots());
        cr.setUnitVotesList(votesList);
        cr.setCounty(districtService.getCounty(crdm.getCountyId()));
        return cr;
    }

    @Transactional
    public CountyRepresentation confirmResultForCounty(Long countyId, boolean isSingleMandate) {
        County county = districtService.getCounty(countyId);
        CountyResult cResult = getResult(county, isSingleMandate);
        cResult.setConfirmed(true);
        countyResultRepository.update(cResult);
        return new CountyRepresentation(county);
    }

    public CountyRepresentation deleteResultForCounty(Long countyId, boolean isSingleMandate) {
        County county = districtService.getCounty(countyId);
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
