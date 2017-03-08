package voting.service;

import voting.dto.countyrep.CountyRepresentativeData;
import voting.model.CountyRep;

import java.util.List;

/**
 * Created by domas on 1/10/17.
 */
public interface CountyRepService {

    CountyRep addNewCountyRep(CountyRepresentativeData countyRepData);

    void deleteCountyRep(Long id);

    CountyRep getCountyRep(Long id);

    List<CountyRep> getCountyReps();

    CountyRep getCountyRepByUsername(String username);
}
