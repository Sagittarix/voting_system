package voting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import voting.dto.CountyData;
import voting.model.County;
import voting.service.CountyService;

/**
 * Created by andrius on 1/21/17.
 */

@RestController
@RequestMapping("/api/county")
public class CountyController {

    @Autowired
    private CountyService countyService;

    @PostMapping
    public County create(@RequestBody CountyData countyData) {
        return countyService.saveWithDistrict(countyData);
    }

}
