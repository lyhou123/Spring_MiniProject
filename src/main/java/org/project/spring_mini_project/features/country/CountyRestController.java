package org.project.spring_mini_project.features.country;

import lombok.RequiredArgsConstructor;
import org.project.spring_mini_project.features.country.dto.CountryRespone;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/countries")
@RequiredArgsConstructor
public class CountyRestController {

    private final CountryService countryService;

    @GetMapping("/countries")
    public List<CountryRespone> getAllCountries(@RequestParam(required = false) String sortBy,
                                                @RequestParam(required = false) String filterByName) {
        return countryService.getAllCountries(sortBy, filterByName);
    }
}
