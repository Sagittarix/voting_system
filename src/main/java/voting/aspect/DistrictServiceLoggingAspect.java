package voting.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import voting.dto.CandidateRepresentation;
import voting.dto.CountyData;
import voting.dto.DistrictRepresentation;
import voting.service.DistrictService;
import voting.utils.Extractor;

import java.util.Arrays;
import java.util.List;

/**
 * Created by andrius on 2/26/17.
 */

@Aspect
@Component
public class DistrictServiceLoggingAspect {

    private final DistrictService districtService;
    private final Logger logger = Logger.getLogger(DistrictServiceLoggingAspect.class);

    @Autowired
    public DistrictServiceLoggingAspect(DistrictService districtService) {
        this.districtService = districtService;
    }

    @Pointcut("execution(* voting.controller.DistrictController.getDistricts())")
    void getDistricts() { }

    @After("getDistricts()")
    public void afterGetAllDistricts(JoinPoint jp) {
        logger.debug("All districts extracted : " + jp.toLongString());
    }

    @Pointcut("execution(* voting.controller.DistrictController.getDistrict(Long))")
    void getDistrictById() { }

    @AfterReturning(pointcut = "getDistrictById()", returning = "returnValue")
    public void afterGetDistrictById(JoinPoint jp, DistrictRepresentation returnValue) {
        logger.debug(String.format("District extracted [id: %d] : %s", returnValue.getId(), jp.toLongString()));
    }

    @Pointcut("execution(* voting.controller.DistrictController.addNewDistrict(..))")
    void addNewDistrict() { }

    @AfterReturning(value = "addNewDistrict()", returning = "returnValue")
    public void afterCreatingNewDistrict(JoinPoint jp, DistrictRepresentation returnValue) {
        String[] ids = Extractor.extractIdsFromCounties(returnValue.getCounties());
        logger.debug(String.format(
                "District [id: %d] created with counties [ids: %s] : %s",
                returnValue.getId(), Arrays.toString(ids), jp.toLongString())
        );
    }

    @Pointcut("execution(* voting.controller.DistrictController.deleteDistrict(..)) && args(id)")
    void deleteDistrict(Long id) { }

    @AfterReturning(value = "deleteDistrict(id)", argNames = "jp,id")
    public void afterDeletingDistrict(JoinPoint jp, Long id) {
        logger.debug(String.format(
                "District deleted [id: %d] : %s",
                id, jp.toLongString())
        );
    }

    @Pointcut("execution(* voting.controller.DistrictController.deleteCandidateList(..)) && args(id)")
    void deleteCandidateList(Long id) { }

    @AfterReturning(value = "deleteCandidateList(id)", argNames = "jp,id")
    public void afterDeletingDistrictCandidateList(JoinPoint jp, Long id) {
        String[] ids = Extractor.extractIdsFromCandidates(
                new DistrictRepresentation(districtService.getDistrict(id)).getCandidates()
        );
        logger.debug(String.format(
                "District [id: %d] candidates removed [ids: %s] : %s",
                id, Arrays.toString(ids), jp.toLongString())
        );
    }

    @Pointcut("execution(* voting.controller.DistrictController.setCandidateList(..)) && args(id)")
    void setCandidateList(Long id) { }

    @AfterReturning(value = "setCandidateList(id)", argNames = "jp,id,returnValue", returning = "returnValue")
    public void afterSettingDistrictCandidates(JoinPoint jp, Long id, DistrictRepresentation returnValue) {
        String[] ids = Extractor.extractIdsFromCandidates(returnValue.getCandidates());
        logger.debug(String.format(
                "District [id: %d] has uploaded candidates [ids: %s] : %s",
                id, Arrays.toString(ids), jp.toLongString())
        );
    }

    @Pointcut("execution(* voting.controller.DistrictController.getCandidateList(..)) && args(id)")
    void getCandidateList(Long id) { }

    @AfterReturning(value = "getCandidateList(id)", argNames = "jp,id,returnValue", returning = "returnValue")
    public void afterGettingDistrictCandidates(JoinPoint jp, Long id, List<CandidateRepresentation> returnValue) {
        String[] ids = Extractor.extractIdsFromCandidates(returnValue);
        logger.debug(String.format(
                "District [id: %d] has uploaded candidates [ids: %s] : %s",
                id, Arrays.toString(ids), jp.toLongString())
        );
    }

    @Pointcut(
            value = "execution(* voting.controller.DistrictController.addCounty(..)) && args(id, countyData)",
            argNames = "id,countyData")
    void addCounty(Long id, CountyData countyData) { }

    @AfterReturning(
            value = "addCounty(id, countyData)",
            argNames = "jp,id,countyData,returnValue",
            returning = "returnValue")
    public void afterAddingCounty(JoinPoint jp, Long id, CountyData countyData, DistrictRepresentation returnValue) {
        Long cid = returnValue.getCounties().stream()
                                            .filter(c -> c.getName().equals(countyData.getName()))
                                            .findFirst()
                                            .get()
                                            .getId();
        logger.debug(String.format(
                "County [id: %d] created [district id: %d] : %s",
                cid, id, jp.toLongString())
        );
    }

    @Pointcut("execution(* voting.controller.DistrictController.deleteCounty(..)) && args(id)")
    void deleteCounty(Long id) { }

    @AfterReturning(value = "deleteCounty(id)", argNames = "jp,id")
    public void aroundDeleteCounty(JoinPoint jp, Long id) {
        logger.debug(String.format(
                "County [id: %d] deleted : %s",
                id, jp.toLongString())
        );
    }
}
