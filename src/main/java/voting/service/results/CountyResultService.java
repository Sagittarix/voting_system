//package voting.service.results;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import voting.dto.CountyDTO;
//import voting.dto.results.CountyResultData;
//import voting.exception.NotFoundException;
//import voting.model.County;
//import voting.model.results.CountyResult;
//import voting.model.results.Vote;
//import voting.repository.results.CountyResultRepository;
//import voting.service.DistrictService;
//
//import java.util.Date;
//import java.util.List;
//
///**
// * Created by andrius on 1/24/17.
// */
//
//@Service
//public class CountyResultService {
//
//    private DistrictService districtService;
//    private CountyResultRepository countyResultRepository;
//    private UnitVotesService unitVotesService;
//
//    @Autowired
//    public CountyResultService(CountyResultRepository countyResultRepository, DistrictService districtService, UnitVotesService unitVotesService) {
//        this.countyResultRepository = countyResultRepository;
//        this.districtService = districtService;
//        this.unitVotesService = unitVotesService;
//    }
//
//
//    public List<CountyResult> getAllCountyResults() {
//        return (List<CountyResult>) countyResultRepository.findAll();
//    }
//
//    public List<CountyResult> getAllSingleMandateResults() {
//        return (List<CountyResult>) countyResultRepository.findBySingleMandateSystemTrue();
//    }
//
//    public List<CountyResult> getAllMultiMandateResults() {
//        return (List<CountyResult>) countyResultRepository.findBySingleMandateSystemFalse();
//    }
//
//    @Transactional
//    public CountyResult save(CountyResultData crdm) {
//        return countyResultRepository.save(mapDataWithCollectionToEntity(crdm));
//    }
//
//    public CountyResult getSingleMandateResult(Long countyId) {
//        County county = districtService.getCounty(countyId);
//        return countyResultRepository.findOneByCountyAndSingleMandateSystemTrue(county);
//    }
//
//    public CountyResult getMultiMandateResult(Long countyId) {
//        County county = districtService.getCounty(countyId);
//        return countyResultRepository.findOneByCountyAndSingleMandateSystemFalse(county);
//    }
//
//
//    public CountyResult mapDataWithCollectionToEntity(CountyResultData crdm) {
//        CountyResult cr = new CountyResult();;
//        List<Vote> votesList = unitVotesService.mapCollectionDataToEntities(
//                crdm.getUnitVotesDataModelsList(),
//                crdm.isSingleMandateSystem(),
//                cr);
//        cr.setSingleMandateSystem(crdm.isSingleMandateSystem());
//        cr.setSpoiledBallots(crdm.getSpoiledBallots());
//        cr.setUnitVotesList(votesList);
//        cr.setCounty(districtService.getCounty(crdm.getCountyId()));
//        return cr;
//    }
//
//    @Transactional
//    public CountyResult confirmResultForCounty(Long countyId, boolean isSingleMandate) {
//        County county = districtService.getCounty(countyId);
//        CountyResult cResult = getResult(county, isSingleMandate);
//        cResult.setConfirmed(true);
//        // TODO: temp hack
//        cResult.setConfirmedOn(new Date());
//        return countyResultRepository.save(cResult);
//    }
//
//    public CountyDTO deleteResultForCounty(Long countyId, boolean isSingleMandate) {
//        County county = districtService.getCounty(countyId);
//        CountyResult cr = getResult(county, isSingleMandate);
//        countyResultRepository.delete(cr);
//        county.removeResult(cr);
//        return new CountyDTO(county);
//    }
//
//    private CountyResult getResult(County county, boolean isSingleMandate) {
//        return county.getCountyResultList()
//                     .stream()
//                     .filter(cr -> cr.isSingleMandateSystem() == isSingleMandate)
//                     .findFirst()
//                     .get();
//    }
//
//
//    private void throwNotoundIfNull(Object object, String message) {
//        if (object == null) {
//            throw new NotFoundException(message);
//        }
//    }
//}
