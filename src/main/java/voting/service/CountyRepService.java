package voting.service;

import voting.dto.CountyRepresentativeData;
import voting.dto.CountyRepresentativeRepresentation;
import voting.model.CountyRep;

import java.util.List;

/**
 * Created by domas on 1/10/17.
 */
public interface CountyRepService {

    CountyRep addNewCountyRep(CountyRepresentativeData countyRepData);

    void deleteCountyRep(Long id);

    CountyRepresentativeRepresentation getCountyRep(Long id);

    List<CountyRep> getCountyReps();
}
